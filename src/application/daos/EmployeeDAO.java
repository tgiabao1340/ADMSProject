package application.daos;

import java.util.List;

import application.entities.Account;
import application.entities.Employee;

public class EmployeeDAO extends GeneralCRUD<Employee> {

	public Employee findByAc(Account ac) {
		List<Employee> list = this.getAll(Employee.class);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccount().equals(ac))
				return list.get(i);
		}
		return null;
	}

	public Employee findById(String id) {
		List<Employee> list = this.getAll(Employee.class);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAccount().getAccountID().equalsIgnoreCase(id))
				return list.get(i);
		}
		return null;
	}
}
