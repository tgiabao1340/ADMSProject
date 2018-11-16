package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ReplacementID", referencedColumnName = "ProductID")
public class Replacement extends Product {

	@Column(name = "Description", columnDefinition = "nvarchar(60)")
	private String description;

	public Replacement() {
		super();
	}

	public Replacement(String productName, String status, int warranty, double unitPrice, int quantity, Model model,
			Supplier supplier, String description) {
		super(productName, status, warranty, unitPrice, quantity, model, supplier);
		this.description = description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Replacement [description=" + description + ", productID=" + productID + ", productName=" + productName
				+ ", status=" + status + ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
	}

}
