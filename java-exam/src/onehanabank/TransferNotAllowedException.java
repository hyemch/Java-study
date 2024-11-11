package onehanabank;

public class TransferNotAllowedException extends Exception {
	public TransferNotAllowedException(String message) {
		super(message);
		System.out.println("Transfer not allowed : " + message);
	}
}
