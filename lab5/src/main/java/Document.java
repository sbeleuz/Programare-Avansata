import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;  //file name or Web page
    private Map<String, Object> tags = new HashMap<>();

    public Document() {
    }

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    /**
     * Represent document's content as plain text (each attribute on a new line)
     * @return Document's plain text representation
     */
    public String toPlainText() {
        StringBuilder retSB = new StringBuilder(String.format("%s\r\n%s\r\n%s\r\n", id, name, location));
        for (Map.Entry<String, Object> entry : tags.entrySet())
            retSB.append(entry.getKey()).append("\r\n").append(entry.getValue()).append("\r\n");

        return retSB.toString();
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }
}
