import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    List<Document> documents = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(String id) {
        for (Document document : documents) {
            if (document.getId().equals(id))
                return document;
        }
        return null;
    }

    /**
     * Represent catalog's content as plain text (each attribute on a new line)
     * @return Catalog's plain text representation
     */
    public String toPlainText() {
        StringBuilder retSB = new StringBuilder(String.format("%s\r\n%s\r\n/\r\n", name, path));
        for (Document doc : documents)
            retSB.append(doc.toPlainText()).append("/\r\n");

        return retSB.toString();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}
