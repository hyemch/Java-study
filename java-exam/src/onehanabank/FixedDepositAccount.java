package onehanabank;

import java.util.ArrayList;

public class FixedDepositAccount extends Account {
	public FixedDepositAccount(int accountNo, String name, double balance) {
		super(accountNo, "정기예금", name, balance);
	}

	@Override
	public void showInfo() {
		String rate = """
					* 예치 개월에 따른 적용 금리
					1개월 이상 3.0%
					3개월 이상 3.35%
					6개월 이상 3.4%
					9개월 이상 3.35%
					12개월 이상 3.35%
					24개월 이상 2.9%
					36개월 이상 2.9%
					48개월 이상 2.9%
			""";
		System.out.printf("%s 통장 (계좌번호: %d, 예치금: %,.0f원, 예금주: %s)\n%s\n", accountName, accountNo, balance, name, rate);
	}

	@Override
	public String selectMenu() {
		return scanner.scanLine("> 정기예금이 만기되었습니다. (+: 만기처리, -: 출금, T: 이체, I: 정보)");
	}

	// @Override
	// public void deposit(double amount) {
	//
	// }

	@Override
	public void withdraw(double amount) throws WithdrawNotAllowedException {
		try {
			throw new WithdrawNotAllowedException("출금할 수 없는 통장입니다.");
		} catch (WithdrawNotAllowedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void transfer(ArrayList<Account> accounts) {
		try {
			throw new TransferNotAllowedException("이체할 수 없는 통장입니다.");
		} catch (TransferNotAllowedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void maturity(double amount, Account toAccount) {
		this.balance = 0;
		toAccount.deposit(amount);
		System.out.printf("%s에 %,.0f원이 입금되었습니다.\n"
			+ "정기예금 통장은 해지되었습니다. 감사합니다.", accountName, amount);
	}

	// public void maturity(int months, StringBuilder toAccountList) {
	// 	double interestRate = calculateInterestRate(months);
	// 	while (true) {
	// 		String check = scanner.scanLine("%d개월(적용금리 %.2f)로 만기 처리하시겠어요? (y/n)".formatted(months,
	// 			interestRate));
	// 		if (check.isEmpty() || check.equals("0")) {
	// 			continue;
	// 		}
	// 		if (check.equalsIgnoreCase("y")) {
	// 			// 만기 처리 및 이체 로직
	// 			double maturityAmount = balance * (1 + interestRate * months / 12);
	// 			deposit(maturityAmount);
	// 		} else {
	// 			System.out.println("만기처리가 취소되었습니다.");
	// 			continue;
	// 		}
	// 		break;
	// 	}
	// 만기 처리 후 종료
	// }

	public double calculateMaturityAmount(int months, double interestRate) {
		return balance * (1 + interestRate * months / 12);
	}

	public double calculateInterestRate(int months) {
		if (months >= 48) {
			return 0.029;
		} else if (months >= 36) {
			return 0.029;
		} else if (months >= 24) {
			return 0.029;
		} else if (months >= 12) {
			return 0.0335;
		} else if (months >= 9) {
			return 0.0335;
		} else if (months >= 6) {
			return 0.034;
		} else if (months >= 3) {
			return 0.0335;
		} else if (months >= 1) {
			return 0.03;
		}
		return 0;
	}
}

// @Override
// public void withdraw(double amount) throws WithdrawNotAllowedException {
// 	throw new WithdrawNotAllowedException("출금할 수 없는 계좌입니다.");
// }
//
// @Override
// public void transfer(Account targetAccount, double amount) throws TransferNotAllowedException {
// 	throw new TransferNotAllowedException("이체할 수 없는 계좌입니다.");
// }

// public void maturityProcess(Account targetAccount) throws Exception {
// int months = scanner.scanInt("예치 개월수를 입력하세요(1~60개월): ");
// interestRate = calculateInterestRate(depositMonths);
// System.out.println(depositMonths + "개월 (" + interestRate + "% 적용)로 만기 처리됩니다. 최종 금액: " + maturityAmount + "원");
// this.balance = 0; //정기예금 해지
// targetAccount.deposit(maturityAmount);
// System.out.printf("정기예금 만기처리 완료 : %s에 만기 금액 %,.0f원이 이체되었습니다.%n", targetAccount.accountName, maturityAmount);