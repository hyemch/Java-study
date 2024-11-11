package onehanabank;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String message) {
		super(message);
		System.out.println("Insufficient Balance: " + message);
	}
}
