package application.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import application.entities.Customer;
import application.entities.Order;

public class CustomerDAO extends GeneralCRUD<Customer> {
	@SuppressWarnings("unchecked")
	public List<Order> getListCustomerbyName(String name) {
		String s = "From " + Customer.class.getName() + " where CustomerID like '" + name.toUpperCase() + "%'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	public Customer findbyPhone(String phone) {
		List<Customer> list = this.getAll(Customer.class);
		for (int i = 0; i < list.size(); i++) {
			Customer customer = list.get(i);
			if (customer.getPhoneNumber().equalsIgnoreCase(phone))
				return customer;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAnumberOfrecordsDesc(int a) {
		String s = "From " + Order.class.getName() + " order by Date desc";
		Query q = em.createQuery(s).setMaxResults(a);
		List<Customer> customers = new ArrayList<Customer>();
		List<Order> orders = q.getResultList();
		orders.forEach(x -> {
			if (!customers.contains(x.getCustomer())) {
				customers.add(x.getCustomer());
			}
		});
		return customers;
	}
}
