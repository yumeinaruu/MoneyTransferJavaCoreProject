package exception;

public class NotEnoughMoneyException extends RuntimeException {
    @Override
    public String toString() {
        return "Not enough money to make a transaction";
    }
}
