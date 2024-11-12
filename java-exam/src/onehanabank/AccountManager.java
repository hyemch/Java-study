package onehanabank;

import java.util.ArrayList;
import java.util.Collections;

import onehanabank.myexception.InsufficientBalanceException;
import onehanabank.myexception.WithdrawNotAllowedException;

public class AccountManager {
	private final ArrayList<Account> accounts;
	private final MyScanner scanner;

	public AccountManager() {
		this.accounts = new ArrayList<>();
		this.scanner = new MyScanner();
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public Account selectAccount() {
		StringBuilder accountList = AccountFinder.getAccountList(accounts);
		while (true) {
			String choice = scanner.scanLine(">> 통장을 선택하세요. ( " + accountList + ") ");
			if (choice.isEmpty() || choice.equals("0")) {
				break;
			}
			try {
				int choiceNum = Integer.parseInt(choice);
				for (Account account : accounts) {
					if (account.getAccountNo() == choiceNum) {
						return account;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("유효한 계좌번호를 입력해 주세요.");
			}
			System.out.println("일치하는 통장이 없습니다.");
		}
		return null;
	}

	public void startBankManagement() throws
		WithdrawNotAllowedException {
		System.out.println("OneHanaBank에 오신 것을 환영합니다.");
		while (true) {
			Account account = selectAccount();
			if (account == null) {
				System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
				scanner.close();
				break;
			}
			executeBankManager(account);
		}
	}

	private void executeBankManager(Account account) throws
		WithdrawNotAllowedException {
		Collections.sort(this.accounts);
		account.showInfo();
		while (true) {
			String choice = account.selectMenu();
			if (choice.isEmpty() || choice.equals("0")) {
				break;
			}
			switch (choice) {
				case "+" -> {
					if (account instanceof MaturityProcessable) {
						if (((FixedDepositAccount)account).processMaturity(account, accounts)) {
							return;
						}
					} else {
						account.deposit(0);
					}
				}
				case "-" -> {
					try {
						account.withdraw(0);
					} catch (InsufficientBalanceException e) {
						System.out.println(e.getMessage());
					}
				}
				case "T", "t" -> {
					account.transfer(accounts);
				}
				case "I", "i" -> {
					account.showInfo();
				}
				default -> {
					System.out.println("잘못된 요청입니다.");
				}
			}
		}
	}
}
