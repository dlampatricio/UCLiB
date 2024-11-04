package library;

public class NonExistentItemException extends Exception {

    public NonExistentItemException() {
        super("That item does not exist in our library.");
    }

}
