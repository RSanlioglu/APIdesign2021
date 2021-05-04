package Exception;

/**
 * When trying to create a new file that already exists, you will receive this exception
 */
public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
