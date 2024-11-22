package onehanabank;

import onehanabank.myexception.WithdrawNotAllowedException;

public class Main {
	public static void main(String[] args) throws
		WithdrawNotAllowedException {
		AccountManager accountManagerManager = new AccountManager();

		accountManagerManager.getAccounts().add(new SavingAccount(1, "홍길동", 0));
		accountManagerManager.getAccounts().add(new OverdraftAccount(3, "홍길동", 2000));
		accountManagerManager.getAccounts().add(new FixedDepositAccount(2, "홍길동", 50000000));

		accountManagerManager.startBankManagement();
	}
}
