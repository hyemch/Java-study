package onehanabank;

import java.util.ArrayList;
import java.util.Collections;

public class AccountFinder {
	public static StringBuilder getToAccountList(int accountNo, ArrayList<Account> accounts) {
		Collections.sort(accounts);
		StringBuilder toAccountList = new StringBuilder();
		for (Account value : accounts) {
			if (value.getAccountNo() == accountNo)
				continue;
			toAccountList.append(value).append(" ");
		}
		return toAccountList;
	}

	public static Account findAccountByNumber(int accountNo, ArrayList<Account> accounts) {
		return accounts.stream()
			.filter(account -> account.getAccountNo() == accountNo)
			.findFirst()
			.orElse(null);
	}
}
