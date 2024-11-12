package onehanabank;

public class WithdrawNotAllowedException extends Exception {
	public WithdrawNotAllowedException(String message) {
		super("Withdraw not allowed : " + message);
	}
}
