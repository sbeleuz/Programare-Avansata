import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.validator.routines.UrlValidator;

public class CatalogUtil {

    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static void saveAsText(Catalog catalog) throws IOException {
        Files.writeString(Paths.get(catalog.getPath()), catalog.toPlainText());
    }

    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Catalog) ois.readObject();
        }
    }

    public static Catalog loadAsText(String path) throws IOException, InvalidCatalogException {
        // check if file to load exists
        File catalogFile = new File(path);
        if (!catalogFile.exists()) throw new InvalidCatalogException("Catalog's path not found!");

        // use a scanner to parse each line of file and get catalog's attributes
        Scanner catalogScanner = new Scanner(new File(path));
        catalogScanner.useDelimiter("\r\n"); // EOF is delimiter

        // get catalog's attributes
        Catalog catalog = new Catalog();
        String catalogName = catalogScanner.next();
        catalog.setName(catalogName);
        String catalogPath = catalogScanner.next();
        catalog.setPath(catalogPath);

        catalogScanner.next(); // first separator for documents

        while (catalogScanner.hasNext()) {
            // get current document's attributes
            Document document = new Document();
            String docId = catalogScanner.next();
            document.setId(docId);
            String docName = catalogScanner.next();
            document.setName(docName);
            String docLocation = catalogScanner.next();
            document.setLocation(docLocation);

            catalogScanner.useDelimiter("/"); // each "/" delimiters a new document
            // parse current document's tags
            Scanner tagsScanner = new Scanner(catalogScanner.next());
            tagsScanner.useDelimiter("\r\n");
            while (tagsScanner.hasNext()) {
                String tagKey = tagsScanner.next();
                String tagObj = tagsScanner.next();
                document.addTag(tagKey, tagObj);
            }
            // go for next document
            catalogScanner.useDelimiter("\r\n");
            catalogScanner.next(); // "/"

            catalog.add(document);
        }

        return catalog;
    }

    public static void view(Document doc) throws URISyntaxException, IOException, InvalidDocumentException {
        Desktop desktop = Desktop.getDesktop();
        //browse or open, depending of the location type
        if (doc.getLocation().startsWith("http") || doc.getLocation().startsWith("https")) {
            // check if it's a valid URL
            UrlValidator urlValidator = new UrlValidator();
            if (!urlValidator.isValid(doc.getLocation()))
                throw new InvalidDocumentException("Document's URL is not valid!");
            desktop.browse(new URI(doc.getLocation()));
        } else {
            File docFile = new File(doc.getLocation());
            if (!docFile.exists()) throw new InvalidDocumentException("Document's location doesn't exist!");
            desktop.open(docFile);
        }
    }

}
