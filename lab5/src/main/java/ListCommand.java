public class ListCommand extends Command{

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void executeCommand() {
        System.out.println(catalog.toString());
    }
}
