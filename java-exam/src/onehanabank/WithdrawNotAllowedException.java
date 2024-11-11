package onehanabank;

public class WithdrawNotAllowedException extends Exception {
	public WithdrawNotAllowedException(String message) {
		super(message);
		System.out.println("Withdraw not allowed : " + message);
	}
}
