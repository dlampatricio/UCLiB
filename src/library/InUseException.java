package library;

public class InUseException extends Exception {

    public InUseException() {
        super("At this time this item is in use, please stand by");
    }
}
