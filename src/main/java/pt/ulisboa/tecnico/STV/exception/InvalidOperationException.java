package pt.ulisboa.tecnico.STV.exception;

public class InvalidOperationException extends Exception {
    public InvalidOperationException() {
        super();
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}