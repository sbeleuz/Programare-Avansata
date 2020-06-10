public class InvalidDocumentException extends Exception{
    public InvalidDocumentException(String message) {
        super("Invalid document exception:" + message);
    }
}
