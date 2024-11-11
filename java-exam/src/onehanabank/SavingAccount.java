package onehanabank;

import java.util.ArrayList;

public class SavingAccount extends Account {
	public SavingAccount(int accountNo, String name, double balance) {
		super(accountNo, "자유입출금", name, balance);
	}

	@Override
	public void showInfo() {
		System.out.printf("%s 통장 (계좌번호: %d, 잔액: %,.0f원, 예금주: %s)%n", accountName, accountNo, balance, name);
	}

	@Override
	public String selectMenu() {
		return scanner.scanLine("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보)");
	}

	// public void executeMenu(ArrayList<Account> accounts) throws InsufficientBalanceException {
	// 	String choice = scanner.scanLine("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보)");
	// 	switch (choice) {
	// 		case "+" -> {
	// 			double amount = scanner.scanDouble("입금할 금액을 입력해주세요 :");
	// 			deposit(amount);
	// 		}
	// 		case "-" -> {
	// 			try {
	// 				this.withdraw();
	// 			} catch (InsufficientBalanceException e) {
	// 				System.out.println(e.getMessage());
	// 			}
	// 		}
	// 		case "T" -> {
	// 			transfer(accounts, amount);
	// 		}
	// 		case "I" -> {
	// 			this.showInfo();
	// 		}
	// 		case "0", "" -> {
	// 		}
	// 		default -> {
	// 			System.out.println("잘못된 요청입니다. 이전 메뉴로 돌아갑니다.");
	// 		}
	// 	}
	// }

	// @Override
	// public void deposit(double amount) {
	// 	if (amount == -1) {
	// 		amount = scanner.scanDouble("출금할 금액을 입력해주세요 :");
	// 	}
	// 	if (amount > 0) {
	// 		balance += amount;
	// 		System.out.printf("%s 계좌에 %,.0f원이 입금되었습니다.%n", accountName, amount);
	// 	}
	// }

	@Override
	public void withdraw(double amount) throws InsufficientBalanceException {
		if (amount == 0) {
			amount = scanner.scanDouble("출금할 금액을 입력해주세요 :");
		}
		if (balance < amount) {
			throw new InsufficientBalanceException("잔액이 부족합니다! (잔액 : %,.0f원)".formatted(balance));
		}
		balance -= amount;
		System.out.printf("자유 입출금  총장에서 %,.0f원이 출금되었습니다.\n", amount);
	}

	// private StringBuilder getToAccountList(ArrayList<Account> accounts) {
	// 	StringBuilder toAccountList = new StringBuilder();
	// 	for (Account value : accounts) {
	// 		if (value.accountNo == accountNo) {
	// 			continue;
	// 		}
	// 		toAccountList.append(value).append(" ");
	// 	}
	// 	return toAccountList;
	// }

	@Override
	public void transfer(ArrayList<Account> accounts) throws InsufficientBalanceException {
		StringBuilder toAccountList = getToAccountList(accounts);

		int toAccount = scanner.scanInt("어디로 보낼까요? 계좌 번호를 입력해주세요(" + toAccountList + "): ");
		for (Account target : accounts) {
			if (target.accountNo == toAccount) {
				double amount = scanner.scanDouble("%s에 보낼 금액을 입력해주세요: ");
				withdraw(amount);
				target.deposit(amount);
				System.out.printf("%s에 %,.0f원이 입금되었습니다.", target.accountName, amount);
				break;
			}
		}
		System.out.println("일치하는 계좌가 없습니다.");
	}
}

// @Override
// public void withdraw(double amount) throws InsufficientBalanceException {
// 	if (balance < amount) {
// 		throw new InsufficientBalanceException("잔액이 부족합니다! (잔액 : %,.0f원)".formatted(balance));
// 	}
// 	balance -= amount;
// 	System.out.printf("자유 입출금  총장에서 %,.0f원이 출금되었습니다.", amount);
// }

// @Override
// public void transfer(Account targetAccount, double amount) throws
// 	TransferNotAllowedException,
// 	WithdrawNotAllowedException,
// 	InsufficientBalanceException {
// 	this.withdraw(amount);
// 	targetAccount.deposit(amount);
// 	System.out.printf("%s에 %,.0f원이 입금되었습니다.", targetAccount.accountName, amount);
//
// }

// double amount = scanner.scanDouble("이체할 금액을 입력해주세요 :");
// StringBuilder accountList = getToAccountList(accounts);
// int toAccount = scanner.scanInt("이체할 계좌의 번호를 입력해주세요 (" + accountList + "): ");
// for (Account value : accounts) {
// 	if (value.accountNo == toAccount) {
// 		value.deposit(amount);
// 		this.withdraw();
// 		System.out.printf("자유 입출금  총장에서 %,.0f원이 출금되었습니다.", amount);
// 		System.out.printf("%s에 %,.0f원이 입금되었습니다.", accountName, amount);
// 		return;
// 	}
// }
// System.out.println("잘못된 입력입니다.");

// @Override
// public void executeBankManager() {
// 		this.showInfo();
// 		String choice = scanner.scanLine("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보)");
// 		switch (choice) {
// 			case "+" -> {
// 				deposit();
// 			}
// 			case "-" -> {
// 				withdraw();
// 			}
// 			case "T" -> {
// 				transfer();
// 			}
// 			case "I" -> {
// 				this.showInfo();
// 			}
// 			case "0", "" -> {
// 			}
// 			default -> {
// 				System.out.println("잘못된 요청입니다. 이전 메뉴로 돌아갑니다.");
// 			}
// 		}
// 	}
