package exception;

public class UnsufficientAmountException extends RuntimeException {
    @Override
    public String toString() {
        return "Cannot transfer a non-sufficient amount of money";
    }
}
