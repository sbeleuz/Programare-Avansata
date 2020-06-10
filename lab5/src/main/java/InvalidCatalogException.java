public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(String message) {
        super("Invalid catalog exception:" + message);
    }
}
