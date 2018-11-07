package application.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class InStockReportDetailPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String inStockReport;
	private String product;
	
	
	
	public InStockReportDetailPK() {
		super();
	}
	
	public InStockReportDetailPK(String inStockReport, String product) {
		super();
		this.inStockReport = inStockReport;
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inStockReport == null) ? 0 : inStockReport.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		InStockReportDetailPK other = (InStockReportDetailPK) obj;
		if (inStockReport == null) {
			if (other.inStockReport != null)
				return false;
		} else if (!inStockReport.equals(other.inStockReport))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	public String getInStockReport() {
		return inStockReport;
	}

	public void setInStockReport(String inStockReport) {
		this.inStockReport = inStockReport;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	
}
