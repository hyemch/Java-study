package onehanabank;

import java.util.ArrayList;
import java.util.Collections;

public class AccountManager {
	private ArrayList<Account> accounts;
	private MyScanner scanner;

	public AccountManager() {
		this.accounts = new ArrayList<>();
		this.scanner = new MyScanner();
	}

	private StringBuilder getAccountList() {
		Collections.sort(this.accounts);
		StringBuilder accountList = new StringBuilder();
		for (Account value : accounts) {
			accountList.append(value.toString()).append(" ");
		}
		return accountList;
	}

	private StringBuilder getToAccountList(int accountNo) {
		Collections.sort(this.accounts);
		StringBuilder toAccountList = new StringBuilder();
		for (Account value : accounts) {
			if (value.accountNo == accountNo) {
				continue;
			}
			toAccountList.append(value).append(" ");
		}
		return toAccountList;
	}

	public Account selectAccount() {
		StringBuilder accountList = getAccountList();
		while (true) {
			String choice = scanner.scanLine(">> 통장을 선택하세요. ( " + accountList + ") ");
			if (choice.isEmpty() || choice.equals("0")) {
				break;
			}
			try {
				int choiceNum = Integer.parseInt(choice);
				for (Account account : accounts) {
					if (account.accountNo == choiceNum) {
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

	public boolean executeMaturity(FixedDepositAccount account) {
		while (true) {
			try {
				String inputMonths = scanner.scanLine("예치 개월수를 입력하세요(1~60개월): ");
				if (inputMonths.isEmpty() || inputMonths.equals("0")) {
					break;
				}
				int months = Integer.parseInt(inputMonths);
				if (months < 1 || months > 60) {
					System.out.println("잘못된 입력입니다. 1~60개월 사이의 값을 입력해 주세요.");
					continue;
				}
				double interestRate = account.calculateInterestRate(months);
				StringBuilder toAccountList = getToAccountList(account.accountNo);
				String check = scanner.scanLine("%d개월(적용금리 %.2f%%)로 만기 처리하시겠어요? (y/n)".formatted(months,
					interestRate));
				if (check.isEmpty() || check.equals("0")) {
					break;
				} else if (check.equalsIgnoreCase("y")) {
					double maturityAmount = account.calculateMaturityAmount(months, interestRate);
					while (true) {
						try {
							String inputToAccount = scanner.scanLine("어디로 보낼까요? 계좌 번호를 입력해주세요(" + toAccountList + "):"
								+ " ");
							if (inputToAccount.isEmpty() || inputToAccount.equals("0")) {
								break;
							}
							int toAccount = Integer.parseInt(inputToAccount);
							for (Account target : accounts) {
								if (target.accountNo == toAccount && target.accountNo != account.accountNo) {
									account.maturity(maturityAmount, target);
									accounts.remove(account);
									return true;
								}
							}
							System.out.println("일치하는 계좌가 없습니다.");
						} catch (NumberFormatException e) {
							System.out.println("올바른 계좌번호를 입력해주세요");
						}
					}
					// int toAccount = scanner.scanInt("어디로 보낼까요? 계좌 번호를 입력해주세요(" + toAccountList + "): ");
				} else if (!check.equalsIgnoreCase("n")) {
					System.out.println("잘못된 입력입니다. 예치 개월수부터 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("유효한 숫자를 입력해주세요: ");
			}
		}
		return false;
	}

	public void startBankManagement() throws
		WithdrawNotAllowedException,
		InsufficientBalanceException,
		TransferNotAllowedException {
		System.out.println("OneHanaBank에 오신 것을 환영합니다.");
		while (true) {
			Account account = selectAccount();
			if (account == null) {
				System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
				break;
			}
			executeBankManager(account);
		}
	}

	private void executeBankManager(Account account) throws
		WithdrawNotAllowedException,
		InsufficientBalanceException,
		TransferNotAllowedException {
		Collections.sort(this.accounts);
		account.showInfo();
		while (true) {
			String choice = account.selectMenu();
			if (choice.isEmpty() || choice.equals("0")) {
				break;
			}
			switch (choice) {
				case "+" -> {
					if (account instanceof FixedDepositAccount) {
						if (executeMaturity((FixedDepositAccount)account)) {
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

	public static void main(String[] args) throws
		WithdrawNotAllowedException,
		InsufficientBalanceException,
		TransferNotAllowedException {
		AccountManager accountManagerManager = new AccountManager();

		accountManagerManager.accounts.add(new SavingAccount(1, "홍길동", 0));
		accountManagerManager.accounts.add(new OverdraftAccount(3, "홍길동", 2000));
		accountManagerManager.accounts.add(new FixedDepositAccount(2, "홍길동", 50000000));

		accountManagerManager.startBankManagement();
	}
}
