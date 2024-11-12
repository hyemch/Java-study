package onehanabank;

import onehanabank.myexception.NegativeAmountException;

public class OverdraftAccount extends Account {
	public OverdraftAccount(int accountNo, String name, double balance) {
		super(accountNo, "마이너스통장", name, balance);
	}

	@Override
	public void showInfo() {
		System.out.printf("%s 통장 - 잔액: %,.0f원 %n", getAccountName(), getBalance());
	}

	@Override
	public String selectMenu() {
		return scanner.scanLine("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보)");
	}

	@Override
	public Double withdraw(double amount) {
		if (amount < 0) {
			System.out.println("출금 금액은 음수가 될 수 없습니다.");
			return null;
		}
		if (amount == 0) {
			while (true) {
				try {
					String input = scanner.scanLine("출금하실 금액을 입력해주세요: ");
					if (input.isEmpty() || input.equals("0")) {
						return null;
					}
					amount = Double.parseDouble(input);
					if (amount < 0) {
						throw new NegativeAmountException("출금 금액은 음수가 될 수 없습니다.");
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("올바른 금액을 입력해주세요");
				} catch (NegativeAmountException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		this.setBalance(getBalance() - amount);
		System.out.printf("%s에서 %,.0f원이 출금되었습니다.잔액은 %,.0f원 입니다.%n", getAccountName(), amount, getBalance());
		return amount;
	}
}
