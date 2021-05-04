package Exceptions;

/**
 * When trying to create a new file that already exists, you will recieve this exception
 */


public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
