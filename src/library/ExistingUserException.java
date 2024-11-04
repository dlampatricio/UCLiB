package library;

public class ExistingUserException extends Exception{

    public ExistingUserException() {
        super("This user already exists in our system.");
    }    
}
