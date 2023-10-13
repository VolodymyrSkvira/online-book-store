package s.volodymyr.onlinebookstore.exception;

public class BookNotSavedException extends RuntimeException {
    public BookNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotSavedException(String message) {
        super(message);
    }
}
