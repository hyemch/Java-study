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
			amount = scanner.scanDouble("출금할 금액을 입력해주세요 :");
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

	public StringBuilder getToAccountList(ArrayList<Account> accounts) {
		StringBuilder toAccountList = new StringBuilder();
		for (Account value : accounts) {
			if (value.accountNo == accountNo) {
				continue;
			}
			toAccountList.append(value).append(" ");
		}
		return toAccountList;
	}

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
