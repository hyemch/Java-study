package onehanabank;

import java.util.ArrayList;

public class OverdraftAccount extends Account {
	public OverdraftAccount(int accountNo, String name, double balance) {
		super(accountNo, "마이너스통장", name, balance);
	}

	@Override
	public void showInfo() {
		System.out.printf("%s 통장 - 잔액: %,.0f원 %n", accountName, balance);
	}

	@Override
	public String selectMenu() {
		return scanner.scanLine("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보)");
	}

	// @Override
	// public void deposit(double amount) {
	//
	//
	// }

	@Override
	public void withdraw(double amount) {
		if (amount == 0) {
			amount = scanner.scanDouble("출금하실 금액을 입력해주세요: ");
		}
		balance -= amount;
		System.out.printf("%s에서 %,.0f원이 출금되었습니다.%n", accountName, amount);

	}

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
// public void withdraw(double amount) {
// 	balance -= amount;
// 	//한도 제한 없음.
// 	System.out.printf("마이너스 통장에서 %,.0f원이 출금되었습니다.%n", amount);
// }
//
// @Override
// public void transfer(Account targetAccount, double amount) throws TransferNotAllowedException {
// 	withdraw(amount);
// 	targetAccount.deposit(amount);
// 	System.out.printf("%d에서 %s로 %,.0f원이 입금되었습니다.", this.accountNo, targetAccount.accountName, amount);
// }
