package application;

import java.util.List;

import application.daos.AccountDAO;
import application.daos.MyEntityManagerFactory;
import application.entities.Account;
import javafx.concurrent.Task;

public class Handler {
	private ErrorAlert error;
	private Account account_using;

	public Handler() {
		// TODO Auto-generated constructor stub
		error = new ErrorAlert();
		// datacenter = new DataCenter();
	}

	public boolean validateLogin(String username, String password) {

		AccountDAO accDAO = new AccountDAO();
		List<Account> list = accDAO.getAll(Account.class);

		for (int i = 0; i < list.size(); i++) {
			Account account = list.get(i);
			if (account.getAccountID().equals(username)) {
				if (account.getPassword().equals(password)) {
					account_using = account;
					return true;
				}
			}
		}
		return false;
	}

	public Account getAccount_using() {
		return account_using;
	}

	public void setAccount_using(Account account_using) {
		this.account_using = account_using;
	}

	public ErrorAlert getError() {
		return error;
	}

	public void setError(ErrorAlert error) {
		this.error = error;
	}

	public Task<Void> loaddata() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				updateProgress(0, 10);
				MyEntityManagerFactory em = MyEntityManagerFactory.getInstance();
				for (int i = 0; i < 10; i++) {
					updateProgress(i, 10);
					Thread.sleep(100);
				}
				Thread.sleep(100);
				updateProgress(10, 10);
				Thread.sleep(50);
				return null;

			}
		};
		return task;
	}
}
