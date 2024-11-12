package onehanabank;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String message) {
		super("Insufficient Balance: " + message);
	}
}
