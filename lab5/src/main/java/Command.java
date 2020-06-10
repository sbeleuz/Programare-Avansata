import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public abstract class Command {
    protected Catalog catalog;

    abstract void executeCommand() throws ClassNotFoundException, IOException, TikaException, SAXException;
}
