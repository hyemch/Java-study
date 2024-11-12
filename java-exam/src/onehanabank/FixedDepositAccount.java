package onehanabank;

import java.util.ArrayList;

public class FixedDepositAccount extends Account implements MaturityProcessable {

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
		System.out.printf("%s 통장 (계좌번호: %d, 예치금: %,.0f원, 예금주: %s)\n%s\n", getAccountName(), getAccountNo(),
			getBalance(), getName(), rate);
	}

	@Override
	public String selectMenu() {
		return scanner.scanLine("> 정기예금이 만기되었습니다. (+: 만기처리, -: 출금, T: 이체, I: 정보)");
	}

	@Override
	public Double withdraw(double amount) {
		try {
			throw new WithdrawNotAllowedException("출금할 수 없는 통장입니다.");
		} catch (WithdrawNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		return null;
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
		this.setBalance(0);
		toAccount.deposit(amount);
		System.out.println("정기예금 통장은 해지되었습니다. 감사합니다.");
	}

	public double calculateMaturityAmount(double interestRate) {
		return getBalance() * (1 + interestRate / 100);
	}

	public double calculateInterestRate(int months) {
		if (months >= 48) {
			return 2.9;
		} else if (months >= 36) {
			return 2.9;
		} else if (months >= 24) {
			return 2.9;
		} else if (months >= 12) {
			return 3.35;
		} else if (months >= 9) {
			return 3.35;
		} else if (months >= 6) {
			return 3.4;
		} else if (months >= 3) {
			return 3.35;
		} else {
			return 3;
		}
	}

	// private StringBuilder getToAccountList(int accountNo, ArrayList<Account> accounts) {
	// 	Collections.sort(accounts);
	// 	StringBuilder toAccountList = new StringBuilder();
	// 	for (Account value : accounts) {
	// 		if (value.getAccountNo() == accountNo) {
	// 			continue;
	// 		}
	// 		toAccountList.append(value).append(" ");
	// 	}
	// 	return toAccountList;
	// }

	// @Override
	// public boolean processMaturity(Account myAccount, ArrayList<Account> accounts) {
	// 	int months = maturityInputHandler.getMonts();
	// 	if (months == 0) {
	// 		return false;
	// 	}
	// 	double interestRate = calculateInterestRate(months);
	//
	// 	while (true) {
	// 		String check = scanner.scanLine("%d개월(적용금리 %.2f%%)로 만기 처리하시겠어요? (y/n)".formatted(months,
	// 			interestRate));
	// 		if (check.isEmpty() || check.equals("0")) {
	// 			return false;
	// 		} else if (check.equalsIgnoreCase("y")) {
	// 			double maturityAmount = calculateMaturityAmount(months, interestRate);
	// 			StringBuilder toAccountList = AccountFinder.getToAccountList(getAccountNo(), accounts);
	// 			int toAccountNo = maturityInputHandler.getToAccountNo(toAccountList.toString());
	// 			if (toAccountNo == 0) {
	// 				return false;
	// 			}
	// 			Account toAccount = AccountFinder.findAccountByNumber(toAccountNo, accounts);
	// 			if (toAccount != null && toAccount.getAccountNo() != myAccount.getAccountNo()) {
	// 				maturity(maturityAmount, toAccount);
	// 				accounts.remove(myAccount);
	// 				return true;
	// 			} else {
	// 				System.out.println("일치하는 계좌가 없습니다.");
	// 			}
	// 		} else if (!check.equalsIgnoreCase("n")) {
	// 			System.out.println("잘못된 입력입니다. 예치 개월수부터 다시 입력해주세요.");
	// 		}
	// 	}
	// }
	//
	// @Override
	// public boolean processMaturity(Account myAccount, ArrayList<Account> accounts) {
	// 	while (true) {
	// 		try {
	// 			String inputMonths = scanner.scanLine("예치 개월수를 입력하세요(1~60개월): ");
	// 			if (inputMonths.isEmpty() || inputMonths.equals("0")) {
	// 				break;
	// 			}
	// 			int months = Integer.parseInt(inputMonths);
	// 			if (months < 1 || months > 60) {
	// 				System.out.println("잘못된 입력입니다. 1~60개월 사이의 값을 입력해 주세요.");
	// 				continue;
	// 			}
	// 			double interestRate = calculateInterestRate(months);
	// 			StringBuilder toAccountList = getToAccountList(getAccountNo(), accounts);
	// 			String check = scanner.scanLine("%d개월(적용금리 %.2f%%)로 만기 처리하시겠어요? (y/n)".formatted(months,
	// 				interestRate));
	// 			if (check.isEmpty() || check.equals("0")) {
	// 				break;
	// 			} else if (check.equalsIgnoreCase("y")) {
	// 				double maturityAmount = calculateMaturityAmount(months, interestRate);
	// 				while (true) {
	// 					try {
	// 						String inputToAccount = scanner.scanLine("어디로 보낼까요? 계좌 번호를 입력해주세요(" + toAccountList + "):"
	// 							+ " ");
	// 						if (inputToAccount.isEmpty() || inputToAccount.equals("0")) {
	// 							break;
	// 						}
	// 						int toAccount = Integer.parseInt(inputToAccount);
	// 						for (Account target : accounts) {
	// 							if (target.getAccountNo() == toAccount
	// 								&& target.getAccountNo() != myAccount.getAccountNo()) {
	// 								maturity(maturityAmount, target);
	// 								accounts.remove(myAccount);
	// 								return true;
	// 							}
	// 						}
	// 						System.out.println("일치하는 계좌가 없습니다.");
	// 					} catch (NumberFormatException e) {
	// 						System.out.println("올바른 계좌번호를 입력해주세요");
	// 					}
	// 				}
	// 			} else if (!check.equalsIgnoreCase("n")) {
	// 				System.out.println("잘못된 입력입니다. 예치 개월수부터 다시 입력해주세요.");
	// 			}
	// 		} catch (NumberFormatException e) {
	// 			System.out.println("유효한 숫자를 입력해주세요: ");
	// 		}
	// 	}
	// 	return false;
	// }
	@Override
	public boolean processMaturity(Account myAccount, ArrayList<Account> accounts) {
		while (true) {
			int months = getValidMonths();
			if (months == -1) { // -1 반환 시 뒤로가기 처리
				return false;
			}

			double interestRate = calculateInterestRate(months);
			while (confirmMaturity(months, interestRate)) {
				double maturityAmount = calculateMaturityAmount(interestRate);
				Account toAccount = getValidTargetAccount(accounts);
				if (toAccount != null) {
					maturity(maturityAmount, toAccount);
					accounts.remove(myAccount);
					return true;
				}
			}
		}
	}

	// 예치 개월수 입력과 검증
	private int getValidMonths() {
		while (true) {
			String input = scanner.scanLine("예치 개월수를 입력하세요(1~60개월): ");
			if (input.isEmpty() || input.equals("0")) {
				return -1; // 뒤로 가기
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

	// 사용자 확인 입력 처리
	private boolean confirmMaturity(int months, double interestRate) {
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

	// 계좌 번호를 입력받고 검증
	private Account getValidTargetAccount(ArrayList<Account> accounts) {
		StringBuilder toAccountList = AccountFinder.getToAccountList(getAccountNo(), accounts);

		while (true) {
			String inputToAccount = scanner.scanLine("어디로 보낼까요? 계좌 번호를 입력해주세요( " + toAccountList + "): ");
			if (inputToAccount.isEmpty() || inputToAccount.equals("0")) {
				return null; // 뒤로 가기
			}
			try {
				int toAccountNo = Integer.parseInt(inputToAccount);
				for (Account target : accounts) {
					if (target.getAccountNo() == toAccountNo && target.getAccountNo() != getAccountNo()) {
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
