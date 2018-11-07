package application;

import java.util.List;

import application.daos.AccountDAO;
import application.daos.CustomerDAO;
import application.daos.MotorbikeDAO;
import application.entities.Account;
import application.entities.Customer;
import application.entities.Motorbike;

public class DataCenter {
	List<Account> listAccount;

	public DataCenter() {

	}

	public void initList() {
//		listOrder = new OrderDAO().getAll(Order.class);
//		listOrderDetail = new OrderDetailDAO().getAll(OrderDetail.class);
		listAccount = new AccountDAO().getAll(Account.class);
//		listEmployee = new EmployeeDAO().getAll(Employee.class);
//		listCustomer = new CustomerDAO().getAll(Customer.class);
//		listMotobike = new MotorbikeDAO().getAll(Motorbike.class);
//		listModel = new ModelDAO().getAll(Model.class);
//		listSuppiler = new SupplierDAO().getAll(Supplier.class);
	}

	public List<Motorbike> findMotorbikeWType(String type) {
		return new MotorbikeDAO().findbyType(type);
	}

	public List<Motorbike> findMotorbikeByTypeWSup(String type, String supname) {
		return new MotorbikeDAO().findbyTypeWSup(type, supname);
	}

	public List<Motorbike> findByName(String name) {
		return new MotorbikeDAO().findbyName(name);
	}

	public Customer findByPhone(String phone) {
		return new CustomerDAO().findbyPhone(phone);
	}

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}
}
