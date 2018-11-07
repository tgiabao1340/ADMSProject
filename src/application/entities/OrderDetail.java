package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(OrderDetailPK.class)
public class OrderDetail {

	@Id
	@ManyToOne
	@JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
	private Order order;
	@Id
	@ManyToOne
	@JoinColumn(name = "MotorbikeID", referencedColumnName = "MotorbikeID")
	private Motorbike motorbike;
	@Id
//	@GenericGenerator(name = "sequence_ord_id", strategy = "entities.OrderDetailGen")
//	@GeneratedValue(generator = "sequence_ord_id")
	@Column(name = "OrderDetailID")
	private String orderDetailID;
	@Column(name = "RegistrationFee")
	private double registrationFee;
	@Column(name = "UnitPrice")
	private double unitPrice;
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "VAT")
	private double vAT;
	@Column(name = "Color", columnDefinition = "nvarchar(20)")
	private String color;

	public OrderDetail() {
		super();
	}

	public OrderDetail(Order order, Motorbike motorbike, double registrationFee, double unitPrice, int quantity,
			double vAT) {
		super();
		this.order = order;
		this.motorbike = motorbike;
		this.registrationFee = registrationFee;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.vAT = vAT;
	}

	public OrderDetail(Order order, Motorbike motorbike, double registrationFee, double unitPrice, int quantity,
			double vAT, String color) {
		super();
		this.order = order;
		this.motorbike = motorbike;
		this.registrationFee = registrationFee;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.vAT = vAT;
		this.color = color;
	}

	public String getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(String orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public Motorbike getMotorbikeID() {
		return motorbike;
	}

	public void setMotorbikeID(Motorbike motorbikeID) {
		motorbike = motorbikeID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getvAT() {
		return vAT;
	}

	public void setvAT(double vAT) {
		this.vAT = vAT;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrderID() {
		return order;
	}

	public void setOrderID(Order orderID) {
		order = orderID;
	}

	public Motorbike getMotorbike() {
		return motorbike;
	}

	public void setMotorbike(Motorbike motorbike) {
		this.motorbike = motorbike;
	}

	public double getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(double registrationFee) {
		this.registrationFee = registrationFee;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getVAT() {
		return vAT;
	}

	public void setVAT(double vAT) {
		this.vAT = vAT;
	}

	public long getTotalRaw() {

		return (long) (quantity * unitPrice);
	}

	public long getTotalVAT() {

		return (long) (quantity * unitPrice * vAT);
	}

	public long getTotalReg() {

		return (long) (quantity * unitPrice * registrationFee);
	}

	public long getSubTotal() {

		return getTotalRaw() + getTotalReg() + getTotalVAT();
	}

	@Override
	public String toString() {
		return "OrderDetail [order=" + order + ", motorbike=" + motorbike + ", orderDetailID=" + orderDetailID
				+ ", registrationFee=" + registrationFee + ", unitPrice=" + unitPrice + ", quantity=" + quantity
				+ ", vAT=" + vAT + ", color=" + color + "]";
	}

}
