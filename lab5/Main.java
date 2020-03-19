import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "C:/Users/stefa/OneDrive/Desktop/catalog.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        try {
            CatalogUtil.save(catalog);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.load("C:/Users/stefa/OneDrive/Desktop/catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

}
