package library;

public class UniqueBookException extends Exception{

    public UniqueBookException() {
        super("This book is a unique copy, it cannot be borrowed.\nYou are welcome to consult it within the library.");
    }
    
}
