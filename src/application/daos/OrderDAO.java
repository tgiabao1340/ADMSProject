package application.daos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import application.entities.Order;

public class OrderDAO extends GeneralCRUD<Order> {

	@SuppressWarnings("unchecked")
	public List<Order> getListOrderbyOrderID(String name) {
		String s = "From " + Order.class.getName() + " where OrderID like '" + name.toUpperCase() + "%'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getListOrderbyEmployeeID(String name) {
		String s = "From " + Order.class.getName() + " where EmployeeID like '" + name.toUpperCase() + "%'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getListOrderbyCustomerID(String name) {
		String s = "From " + Order.class.getName() + " where CustomerID like '" + name.toUpperCase() + "%'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getANumberofrecordsDescbyDate(int a) {
		String s = "From " + Order.class.getName() + " order by Date desc";
		Query q = em.createQuery(s).setMaxResults(a);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByDate(LocalDate d) {
		String s = "From " + Order.class.getName() + " where Date = '" + d + "'";
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByMonth(int m, int y) {
		String s = "From " + Order.class.getName() + " where month(Date) = '" + m + "' and year(Date) = '" + y + "'";
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByYear(int y) {
		String s = "From " + Order.class.getName() + " where year(Date) = '" + y + "'";
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getFromDatetoDate(LocalDate d, LocalDate d2) {
		String s = "From " + Order.class.getName() + " where Date between '" + d + "' and '" + d2 + "'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByEmp(String id) {
		String s = "From " + Order.class.getName() + " where EmployeeID = '" + id + "'";
		System.out.println(s);
		Query q = em.createQuery(s);
		return q.getResultList();
	}
	public int sumOfOrderByDate(LocalDate date) {
		String s = "select count(*) from " + Order.class.getName() + " where Date = '" + date + "'";
		Query q = em.createQuery(s);
		return q.getSingleResult()!=null?Integer.parseInt(q.getSingleResult().toString()):0;
	}
	public int sumOfOrderByMonth(LocalDate date) {
		String s = "select count(*) from " + Order.class.getName() + " where year(Date) = '" + date.getYear() + "' and "+ "month(Date) = '" + date.getMonthValue() + "'";
		Query q = em.createQuery(s);
		return q.getSingleResult()!=null?Integer.parseInt(q.getSingleResult().toString()):0;
	}
	public int sumOfOrderByYear(LocalDate date) {
		String s = "select count(*) from " + Order.class.getName() + " where year(Date) = '" + date.getYear() + "' and "+ "month(Date) = '" + date.getYear() + "'";
		Query q = em.createQuery(s);
		return q.getSingleResult()!=null?Integer.parseInt(q.getSingleResult().toString()):0;
	}
	public int sumOfOrderFromDateToDate(LocalDate date1, LocalDate date2) {
		String s = "select count(*) from " + Order.class.getName() + " where Date between '" + date1 + "' and '" + date2 + "'";
		Query q = em.createQuery(s);
		return q.getSingleResult()!=null?Integer.parseInt(q.getSingleResult().toString()):0;
	}

}

