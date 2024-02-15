package exception;

public class ForwardingException extends RuntimeException {
    public ForwardingException(String message, Throwable cause) {
        super(message, cause);
    }
}