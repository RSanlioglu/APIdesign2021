package Exceptions;

public class FileAlreadyExistsException extends Exception{
    public FileAlreadyExistsException(String message) {
        super(message);
    }
}
