package library;

public class HasNoRightsException extends Exception {

    public HasNoRightsException() {
        super("This user does not have loan rights.");
    }
}
