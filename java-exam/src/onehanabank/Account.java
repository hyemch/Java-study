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

	public abstract Double withdraw(double amount) throws WithdrawNotAllowedException, InsufficientBalanceException;

	private StringBuilder getToAccountList(ArrayList<Account> accounts) {
		StringBuilder toAccountList = new StringBuilder();
		for (Account value : accounts) {
			if (value.accountNo == accountNo) {
				continue;
			}
			toAccountList.append(value).append(" ");
		}
		return toAccountList;
	}

	public void transfer(ArrayList<Account> accounts) throws WithdrawNotAllowedException {
		StringBuilder toAccountList = getToAccountList(accounts);
		while (true) {
			try {
				String inputToAccount = scanner.scanLine("어디로 보낼까요? 계좌 번호를 입력해주세요( " + toAccountList + "):"
					+ " ");
				if (inputToAccount.isEmpty() || inputToAccount.equals("0")) {
					break;
				}
				int toAccount = Integer.parseInt(inputToAccount);
				boolean found = false;
				for (Account target : accounts) {
					if (target.accountNo == toAccount && target.accountNo != accountNo) {
						found = true;
						while (true) {
							String inputAmount = scanner.scanLine("%s에 보낼 금액을 입력해주세요: ".formatted(target.accountName));
							if (inputAmount.isEmpty() || inputAmount.equals("0")) {
								break;
							}
							try {
								double amount = Double.parseDouble(inputAmount);
								Double resultAmount = withdraw(amount);
								if (resultAmount != null) {
									target.deposit(resultAmount);
									return;
								}
							} catch (InsufficientBalanceException e) {
								System.out.println(e.getMessage());
							} catch (NumberFormatException e) {
								System.out.println("올바른 금액을 입력해주세요.");
							}
						}
						break;
					}
				}
				if (!found) {
					System.out.println("일치하는 계좌가 없습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("올바른 계좌번호를 입력해주세요");
			}
		}
	}
	
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
