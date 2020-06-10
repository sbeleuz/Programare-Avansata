import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
        app.testShell();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "C:/Users/stefa/OneDrive/Desktop/catalog.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        Document doc2 = new Document("java2", "Java Course 2", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc2.addTag("type", "Slides");
        doc2.addTag("author", "acf");
        catalog.add(doc2);
        try {
            CatalogUtil.saveAsText(catalog);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.loadAsText("C:/Users/stefa/OneDrive/Desktop/catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        } catch (IOException | URISyntaxException | InvalidDocumentException | InvalidCatalogException e) {
            System.out.println(e.getMessage());
        }
    }

    private void testShell() {
        try {
            Shell shell = new Shell();
            shell.run();
        } catch (IOException | ClassNotFoundException | URISyntaxException | InvalidCommandException | InvalidDocumentException | TikaException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
}
