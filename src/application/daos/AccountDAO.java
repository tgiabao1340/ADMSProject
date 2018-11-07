package application.daos;

import application.entities.Account;

public class AccountDAO extends GeneralCRUD<Account> {

	public boolean CheckLogin(Account ac) {

		if (this.getById(Account.class, new String(ac.getAccountID())).equals(ac))
			return true;
		return false;
	}
}
