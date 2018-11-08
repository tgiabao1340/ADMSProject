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
	@Column(name = "OrderDetailID")
	private String orderDetailID;
	@Column(name = "ChassisNo")
	private String chassisNo;
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


	public OrderDetail(Order order, Motorbike motorbike, String chassisNo, double unitPrice, int quantity,
			double vAT, String color) {
		super();
		this.order = order;
		this.motorbike = motorbike;
		this.chassisNo = chassisNo;
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

	




	public String getChassisNo() {
		return chassisNo;
	}


	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
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


	public long getSubTotal() {

		return this.getTotalRaw()  + this.getTotalVAT();
	}


	@Override
	public String toString() {
		return "OrderDetail [order=" + order.getOrderID() + ", motorbike=" + motorbike.getProductID() + ", orderDetailID=" + orderDetailID
				+ ", chassisNo=" + chassisNo + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", vAT=" + vAT
				+ ", color=" + color + "]";
	}

	

}
