package library;

public class TooManyLoansException extends Exception{

    public TooManyLoansException() {
        super("This user has too many active loans.");
    }
    
}
