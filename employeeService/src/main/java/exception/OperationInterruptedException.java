package exception;

public class OperationInterruptedException extends RuntimeException {

    public OperationInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
