package onehanabank;

public class SavingAccount extends Account {
	public SavingAccount(int accountNo, String name, double balance) {
		super(accountNo, "자유입출금", name, balance);
	}

	@Override
	public void showInfo() {
		System.out.printf("%s 통장 (계좌번호: %d, 잔액: %,.0f원, 예금주: %s)%n", getAccountName(), getAccountNo(), getBalance(),
			getName());
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
					if (getBalance() < amount) {
						throw new InsufficientBalanceException("잔액이 부족합니다! (잔액 : %,.0f원)\n".formatted(getBalance()));
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("올바른 금액을 입력해주세요");
				} catch (InsufficientBalanceException | NegativeAmountException e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			if (getBalance() < amount) {
				System.out.printf("잔액이 부족합니다! (잔액 : %,.0f원)\n".formatted(getBalance()));
				return null;
			}
		}
		setBalance(getBalance() - amount);
		System.out.printf("%s에서 %,.0f원이 출금되었습니다.잔액은 %,.0f원 입니다.%n", getAccountName(), amount, getBalance());
		return amount;
	}
}
