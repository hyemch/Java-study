package onehanabank;

import java.util.ArrayList;

import onehanabank.myexception.TransferNotAllowedException;
import onehanabank.myexception.WithdrawNotAllowedException;

public class FixedDepositAccount extends Account implements MaturityProcessable {
	private final MaturityHandler maturityHandler;

	public FixedDepositAccount(int accountNo, String name, double balance) {
		super(accountNo, "정기예금", name, balance);
		this.maturityHandler = new MaturityHandler(scanner);
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

	@Override
	public boolean processMaturity(Account myAccount, ArrayList<Account> accounts) {
		while (true) {
			int months = maturityHandler.getValidMonths();
			if (months == 0) {
				return false;
			}
			double interestRate = calculateInterestRate(months);
			while (maturityHandler.confirmMaturity(months, interestRate)) {
				double maturityAmount = calculateMaturityAmount(interestRate);
				Account toAccount = maturityHandler.getValidTargetAccount(getAccountNo(), accounts);
				if (toAccount != null) {
					maturity(maturityAmount, toAccount);
					accounts.remove(myAccount);
					return true;
				}
			}
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
}
