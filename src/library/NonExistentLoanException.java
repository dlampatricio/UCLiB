package library;

public class NonExistentLoanException extends Exception{

    public NonExistentLoanException() {
        super("That loan does not exist in our system.");
    }
}
