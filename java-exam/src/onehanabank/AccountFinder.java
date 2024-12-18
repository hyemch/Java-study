package onehanabank;

import java.util.ArrayList;
import java.util.Collections;

public class AccountFinder {

	public static StringBuilder getAccountList(ArrayList<Account> accounts) {
		Collections.sort(accounts);
		StringBuilder accountList = new StringBuilder();
		for (Account value : accounts) {
			accountList.append(value.toString()).append(" ");
		}
		return accountList;
	}

	public static StringBuilder getToAccountList(int accountNo, ArrayList<Account> accounts) {
		Collections.sort(accounts);
		StringBuilder toAccountList = new StringBuilder();
		for (Account value : accounts) {
			if (value.getAccountNo() == accountNo) {
				continue;
			}
			toAccountList.append(value).append(" ");
		}
		return toAccountList;
	}
}
