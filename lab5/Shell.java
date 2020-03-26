import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Shell {
    private Catalog catalog;

    public Shell() {
        catalog = new Catalog();
    }

    public void run() throws IOException, ClassNotFoundException, URISyntaxException, InvalidCommandException, InvalidDocumentException, TikaException, SAXException {
        System.out.println("Welcome to Shell!");
        while (true) {
            // use a scanner to parse input from keyboard
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("[ \n]");

            String commandScanner = scan.next();
            switch (commandScanner) {
                case "exit":
                    System.out.println("Exit successfully!");
                    return;
                case "load": {
                    // load catalog
                    String path = scan.next();
                    if (path.equals("")) throw new InvalidCommandException("Invalid parameter!");
                    LoadCommand command = new LoadCommand(catalog, path);
                    command.executeCommand();
                    catalog = command.getCatalog();
                    System.out.println(catalog.getName() + " - Load successfully!");
                    break;
                }
                case "view": {
                    // view a document from catalog
                    String docId = scan.next();
                    ViewCommand command = new ViewCommand(catalog, docId);
                    command.executeCommand();
                    System.out.println("View successfully!");
                    break;
                }
                case "list": {
                    // show catalog
                    ListCommand command = new ListCommand(catalog);
                    command.executeCommand();
                    System.out.println("List successfully!");
                    break;
                }
                case "report": {
                    // create a report
                    String type = scan.next();
                    String path = scan.next();
                    ReportCommand command = new ReportCommand(catalog, type, path);
                    command.executeCommand();
                    System.out.println("Report created successfully!");
                    break;
                }
                case "info": {
                    // show metadata of file
                    String path = scan.next();
                    if (path.equals("")) throw new InvalidCommandException("Invalid parameter!");
                    InfoCommand command = new InfoCommand(path);
                    command.executeCommand();
                    System.out.println("Info printed successfully!");
                    break;
                }
                default:
                    throw new InvalidCommandException("Unknown command!");
            }
        }
    }
}
