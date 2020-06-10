import java.io.IOException;
import java.net.URISyntaxException;

public class ViewCommand {
    private Catalog catalog;
    private String docId;

    public ViewCommand(Catalog catalog, String docId) {
        this.catalog = catalog;
        this.docId = docId;
    }

    public void executeCommand() throws IOException, URISyntaxException, InvalidDocumentException {
        CatalogUtil.view(catalog.findById(docId));
    }
}
