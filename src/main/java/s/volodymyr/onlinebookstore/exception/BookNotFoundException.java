package s.volodymyr.onlinebookstore.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
