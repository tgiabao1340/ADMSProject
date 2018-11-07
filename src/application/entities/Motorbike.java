package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "MotorbikeID", referencedColumnName = "ProductID")
public class Motorbike extends Product {
	@Column(name = "Capacity")
	private double capacity;
	@Column(name = "Color", columnDefinition = "nvarchar(30)")
	private String color;
	@Column(name = "ManufactureYear")
	private int manufactureYear;
	@Column(name = "Type", columnDefinition = "nvarchar(20)")
	private String type;

	public Motorbike() {
		super();
	}

	public Motorbike(String productName, String status, int warranty, double unitPrice, int quantity, Model model,
			Supplier supplier, double capacity, String color, int manufactureYear, String type) {
		super(productName, status, warranty, unitPrice, quantity, model, supplier);
		this.capacity = capacity;
		this.color = color;
		this.manufactureYear = manufactureYear;
		this.type = type;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Motorbike [capacity=" + capacity + ", color=" + color + ", manufactureYear=" + manufactureYear
				+ ", type=" + type + ", productID=" + productID + ", productName=" + productName + ", status=" + status
				+ ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
	}

}
