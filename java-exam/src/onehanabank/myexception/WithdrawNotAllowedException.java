package onehanabank.myexception;

public class WithdrawNotAllowedException extends Exception {
	public WithdrawNotAllowedException(String message) {
		super("Withdraw not allowed : " + message);
	}
}
