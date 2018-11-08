package application.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "[Order]")
public class Order {

	@Id
	@GenericGenerator(name = "sequence_dep_id", strategy = "application.entities.OrderGen")
	@GeneratedValue(generator = "sequence_dep_id")
	@Column(name = "OrderID")
	private String orderID;
	@Column(name = "Date")
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customer;
	@JoinColumn(name = "OrderComment", columnDefinition = "nvarchar(60)")
	private String orderComment;
	@OneToMany(mappedBy = "order")
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	private List<OrderDetail> listItems;

	public Order() {
		super();

	}

	public Order(String orderID, LocalDate date, Employee employee, Customer customer, String orderComment) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.employee = employee;
		this.customer = customer;
		this.orderComment = orderComment;

	}

	public Order(LocalDate date, Employee employee, Customer customer, String orderComment) {
		super();
		this.date = date;
		this.employee = employee;
		this.customer = customer;
		this.orderComment = orderComment;

	}

	public List<OrderDetail> getListItems() {
		return listItems;
	}

	public void setListItems(final List<OrderDetail> listItems) {
		this.listItems = listItems;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(final String orderID) {
		this.orderID = orderID;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(final String orderComment) {
		this.orderComment = orderComment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		return result;
	}

	public long getTotalRaw() {
		long total = 0l;
		for (int i = 0; i < listItems.size(); i++) {
			total += listItems.get(i).getTotalRaw();
		}
		return total;
	}

	public long getTotalVAT() {
		long total = 0l;
		for (int i = 0; i < listItems.size(); i++) {
			total += listItems.get(i).getTotalVAT();
		}
		return total;
	}


	public long getSubTotal() {

		return getTotalRaw() + getTotalVAT();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Order other = (Order) obj;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", date=" + date + ", employee=" + employee.getBussinessID() + ", customer=" + customer.getBussinessID()
				+ ", orderComment=" + orderComment + ", listItems=" + listItems + "]";
	}

	
}
