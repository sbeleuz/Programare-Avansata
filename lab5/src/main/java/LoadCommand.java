import java.io.IOException;

public class LoadCommand extends Command {
    private String path;

    public LoadCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void executeCommand() throws ClassNotFoundException, IOException {
        catalog = CatalogUtil.load(path);
    }
}
