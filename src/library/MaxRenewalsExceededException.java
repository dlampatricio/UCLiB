package library;

public class MaxRenewalsExceededException extends Exception{

    public MaxRenewalsExceededException() {
        super("The limit of extensions allowed has already been exceeded.");
    }
}
