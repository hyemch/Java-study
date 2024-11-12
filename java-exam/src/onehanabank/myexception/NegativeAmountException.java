package onehanabank.myexception;

public class NegativeAmountException extends Exception {
	public NegativeAmountException(String message) {
		super("Negative Amount is not allowed: " + message);
	}
}
