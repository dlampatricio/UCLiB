package library;

public class NonExistentUserException extends Exception{

    public NonExistentUserException() {
        super("This user does not exists in our system.");
    }
}
