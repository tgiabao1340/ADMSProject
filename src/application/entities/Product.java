package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
	@Id
	@GenericGenerator(name = "sequence_pr_id", strategy = "application.entities.ProductGen")
	@GeneratedValue(generator = "sequence_pr_id")
	@Column(name = "ProductID")
	protected String productID;
	@Column(name = "ProductName", columnDefinition = "nvarchar(50)")
	protected String productName;
	@Column(name = "Status", columnDefinition = "nvarchar(20)")
	protected String status;
	@Column(name = "Warranty")
	protected int warranty;
	@Column(name = "UnitPrice")
	protected double unitPrice;
	@Column(name = "Quantity")
	protected int quantity;
	@ManyToOne
	@JoinColumn(name = "ModelID")
	private Model model;
	@OneToOne
	@JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
	private Supplier supplier;

	public Product() {
		super();
	}

	public Product(String productName, String status, int warranty, double unitPrice, int quantity, Model model,
			Supplier supplier) {
		super();
		this.productName = productName;
		this.status = status;
		this.warranty = warranty;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.model = model;
		this.supplier = supplier;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

//	public List<InStockReportDetail> getDetails() {
//		return details;
//	}
//
//	public void setDetails(List<InStockReportDetail> details) {
//		this.details = details;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", status=" + status
				+ ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", supplier=" + supplier + "]";
	}

}
