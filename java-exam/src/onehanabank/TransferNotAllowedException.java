package onehanabank;

public class TransferNotAllowedException extends Exception {
	public TransferNotAllowedException(String message) {
		super("Transfer not allowed : " + message);
	}
}
