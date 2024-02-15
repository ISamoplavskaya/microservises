package exception;

public class EmptyOrInvalidResponseException extends RuntimeException {
    public EmptyOrInvalidResponseException(String message) {
        super(message);
    }

}
