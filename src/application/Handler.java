package application;

import java.util.List;

import application.daos.AccountDAO;
import application.daos.MyEntityManagerFactory;
import application.entities.Account;
import application.entities.Order;
import javafx.concurrent.Task;

/**
 * Điều tiết các luồng dữ liệu
 * 
 *
 */
public class Handler {
	private ErrorAlert error;
	private Account account_using;
	private Order order_selected;
	private String new_phone;

	public Handler() {
		error = new ErrorAlert();
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

	public Order getOrder_selected() {
		return order_selected;
	}

	public void setOrder_selected(Order order_selected) {
		this.order_selected = order_selected;
	}

	public ErrorAlert getError() {
		return error;
	}

	public void setError(ErrorAlert error) {
		this.error = error;
	}

	/**
	 * Hàm khởi tạo dữ liệu khi khởi chạy ứng dụng
	 * 
	 * @return
	 */
	public Task<Void> loaddata() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				updateProgress(0, 10);
				@SuppressWarnings("unused")
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

	public String getNew_phone() {
		return new_phone;
	}

	public void setNew_phone(String new_phone) {
		this.new_phone = new_phone;
	}
}
