package library;

public class PendentLoanException extends Exception{

    public PendentLoanException() {
        super("Cannot delete user: There are pending loans associated with this user.");
    }
}
