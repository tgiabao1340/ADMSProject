package application.daos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import application.entities.Order;

public class OrderDAO extends GeneralCRUD<Order> {
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
		String s = "From " + Order.class.getName() + " where month(Date) = '" + m + "' and year(Date) = '" + y +"'";
		Query q = em.createQuery(s);
		return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Order> getByYear(int y) {
		String s = "From " + Order.class.getName() + " where year(Date) = '" + y +"'";
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

}
