import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
//        try {
//            //Create JAXB Context
//            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
//            //Create Marshaller
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            //Required formatting??
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            //Writes XML file to file-system
//            jaxbMarshaller.marshal(catalog, new File(catalog.getPath()));
//        } catch (JAXBException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        try (var ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Catalog) ois.readObject();
        }
    }

    public static void view(Document doc) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        //browse or open, depending of the location type
        if (doc.getLocation().startsWith("http") || doc.getLocation().startsWith("https"))
            desktop.browse(new URI(doc.getLocation()));
        else desktop.open(new File(doc.getLocation()));
    }
}
