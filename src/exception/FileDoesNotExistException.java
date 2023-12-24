package exception;

public class FileDoesNotExistException extends RuntimeException {
    @Override
    public String toString() {
        return "There's no files in package. Parsing is meaningless.";
    }
}
