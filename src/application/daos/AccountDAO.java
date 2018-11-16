package application.daos;

import java.util.List;

import application.entities.Account;
import application.entities.Employee;

public class AccountDAO extends GeneralCRUD<Account> {

	public boolean CheckLogin(Account ac) {
		if (this.getById(Account.class, new String(ac.getAccountID())).equals(ac))
			return true;
		return false;
	}
	public Account findById(String id) {
		List<Account> list = this.getAll(Account.class);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccountID().equalsIgnoreCase(id))
				return list.get(i);
		}
		return null;
	}
}
