package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(InStockReportDetailPK.class)
public class InStockReportDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "InStockReportID",referencedColumnName="InStockReportID")
	private InStockReport inStockReport;
	@Id
	@ManyToOne
	@JoinColumn(name = "ProductID",referencedColumnName="ProductID")
	private Product product;
	@Column(name="Quantity")
	private int quantity;
	
	public InStockReportDetail() {
		super();
	}

	public InStockReportDetail(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public InStockReport getInStockReport() {
		return inStockReport;
	}

	public void setInStockReport(InStockReport inStockReport) {
		this.inStockReport = inStockReport;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InStockReportDetail [inStockReport=" + inStockReport + ", product=" + product + ", quantity=" + quantity
				+ "]";
	}
	




	

}
