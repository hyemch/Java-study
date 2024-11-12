package onehanabank;

import java.util.ArrayList;

public class MaturityHandler {
	private final MyScanner scanner;

	public MaturityHandler(MyScanner scanner) {
		this.scanner = scanner;
	}

	public int getValidMonths() {
		while (true) {
			String input = scanner.scanLine("예치 개월수를 입력하세요(1~60개월): ");
			if (input.isEmpty() || input.equals("0")) {
				return 0;
			}
			try {
				int months = Integer.parseInt(input);
				if (months >= 1 && months <= 60) {
					return months;
				} else {
					System.out.println("잘못된 입력입니다. 1~60개월 사이의 값을 입력해 주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("유효한 숫자를 입력해주세요.");
			}
		}
	}

	public boolean confirmMaturity(int months, double interestRate) {
		while (true) {
			String check = scanner.scanLine("%d개월(적용금리 %.2f%%)로 만기 처리하시겠어요? (y/n)".formatted(months, interestRate));
			if (check.isEmpty() || check.equals("0") || check.equals("n")) {
				return false;
			} else if (check.equalsIgnoreCase("y")) {
				return true;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public Account getValidTargetAccount(int accountNo, ArrayList<Account> accounts) {
		StringBuilder toAccountList = AccountFinder.getToAccountList(accountNo, accounts);

		while (true) {
			String inputToAccount = scanner.scanLine("어디로 보낼까요? 계좌 번호를 입력해주세요( " + toAccountList + "): ");
			if (inputToAccount.isEmpty() || inputToAccount.equals("0")) {
				return null;
			}
			try {
				int toAccountNo = Integer.parseInt(inputToAccount);
				for (Account target : accounts) {
					if (target.getAccountNo() == toAccountNo && target.getAccountNo() != accountNo) {
						return target;
					}
				}
				System.out.println("일치하는 계좌가 없습니다.");
			} catch (NumberFormatException e) {
				System.out.println("올바른 계좌번호를 입력해주세요.");
			}
		}
	}

}
