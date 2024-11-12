package onehanabank;

import java.util.ArrayList;

public abstract class Account implements Comparable<Account> {
	MyScanner scanner = new MyScanner();
	protected int accountNo;
	protected String accountName;
	protected String name;
	protected double balance;

	public Account(int accountNo, String accountName, String name, double balance) {
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.name = name;
		this.balance = balance;
	}

	@Override
	public int compareTo(Account account) {
		return Integer.compare(this.accountNo, account.accountNo);
	}

	public abstract void showInfo();

	public abstract String selectMenu();

	public void deposit(double amount) {
		if (amount == 0) {
			while (true) {
				try {
					String input = scanner.scanLine("입금하실 금액을 입력해주세요: ");
					if (input.isEmpty() || input.equals("0")) {
						return;
					}
					amount = Double.parseDouble(input);
					break;
				} catch (NumberFormatException e) {
					System.out.println("올바른 금액을 입력해주세요. ");
				}
			}
		}
		if (amount > 0) {
			balance += amount;
			System.out.printf("%s 계좌에 %,.0f원이 입금되었습니다.%n", accountName, amount);
		}
	}

	public abstract void withdraw(double amount) throws WithdrawNotAllowedException, InsufficientBalanceException;

	public abstract void transfer(ArrayList<Account> accounts) throws
		TransferNotAllowedException,
		InsufficientBalanceException;

	// public void transfer(Account toAccount, double amount) throws TransferNotAllowedException,
	// 	InsufficientBalanceException {
	// 	throw new TransferNotAllowedException("이체할 수 없는 통장입니다.");
	// }

	@Override
	public String toString() {
		return accountNo + ":" + accountName;
	}
}

// @Override
// public void deposit() {
// 	double amount = scanner.scanDouble("입금할 금액을 입력해주세요.");
// 	if (amount > 0) {
// 		balance += amount;
// 		System.out.printf("%s 계좌에 %,.0f원이 입금되었습니다.%n", accountName, amount);
// 	}
// }

// @Override
// public abstract void withdraw() throws WithdrawNotAllowedException, InsufficientBalanceException;

// @Override
// public abstract void transfer() throws
// 	TransferNotAllowedException,
// 	WithdrawNotAllowedException,
// 	InsufficientBalanceException;
