package onehanabank;

import java.util.ArrayList;

public interface MaturityProcessable {
	public boolean processMaturity(Account myAccount, ArrayList<Account> accounts);
}
