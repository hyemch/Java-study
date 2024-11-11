package onehanabank;

public class ExecuteFixedDeposit {
	private final FixedDepositAccount account;

	public ExecuteFixedDeposit(FixedDepositAccount account) {
		this.account = account;
	}
	//
	// public void maturityProcess() throws Exception {
	// 	while (true) {
	// 		int months = account.scanner.scanInt("예치 개월수를 입력하세요(1~60개월): ");
	// 		if (months < 1 || months > 60) {
	// 			System.out.println("잘못된 입력입니다. 1~60개월 사이의 값을 입력해 주세요.");
	// 			continue;
	// 		}
	// 		double interestRate = account.calculateInterestRate(months);
	// 		String check = account.scanner.scanLine("%d개월(적용금리 %.2f)로 만기 처리하시겠어요? (y/n)".formatted(months,
	// 			interestRate));
	// 		if (check.equalsIgnoreCase("y")) {
	// 			// 만기 처리 및 이체 로직
	// 			double maturityAmount = account.balance * (1 + interestRate * months / 12);
	// 			account.deposit(maturityAmount);
	// 		} else {
	// 			System.out.println("만기처리가 취소되었습니다.");
	// 			continue;
	// 		}
	// 		break; // 만기 처리 후 종료
	// 	}
	//
	// }
	//
	// public void execute(String action) throws Exception {
	// 	switch (action) {
	// 		case "+" -> {
	// 			// 만기 처리
	// 			maturityProcess();
	// 		}
	// 		case "-" -> {
	// 			// 출금 처리
	// 		}
	// 		case "T" -> {
	// 			// 이체 처리
	// 		}
	// 		case "I" -> {
	// 			account.showInfo();
	// 		}
	// 		case "0", "" -> {
	// 		}
	// 		default -> {
	// 			System.out.println("잘못된 요청입니다. 이전 메뉴로 돌아갑니다.");
	//
	// 		}
	// 	}
	// }
}
