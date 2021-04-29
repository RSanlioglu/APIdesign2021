package Exceptions;

/**
 * When trying to create a new file that already exists, you will receive this exception
 */
public class FileAlreadyExistsException extends Exception{
    public FileAlreadyExistsException(String message) {
        super(message);
    }
}
