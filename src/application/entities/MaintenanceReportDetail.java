package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(MaintenanceReportDetailPK.class)
public class MaintenanceReportDetail {
	@Id
	@ManyToOne
	@JoinColumn(name = "MaintenanceReportID", referencedColumnName = "MaintenanceReportID")
	private MaintenanceReport maintenanceReport;
	@Id
	@ManyToOne
	@JoinColumn(name = "ReplacementID", referencedColumnName = "ReplacementID")
	private Replacement replacement;
	@Column(name = "Comment", columnDefinition = "nvarchar(50)")
	private String comment;
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "UnitPrice")
	private double unitPrice;

	public MaintenanceReportDetail() {
		super();
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Replacement getReplacement() {
		return replacement;
	}

	public void setReplacement(Replacement replacement) {
		this.replacement = replacement;
	}

	public MaintenanceReport getMaintenanceReport() {
		return maintenanceReport;
	}

	public void setMaintenanceReport(MaintenanceReport maintenanceReport) {
		this.maintenanceReport = maintenanceReport;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getTotalRaw() {

		return (long) (quantity * unitPrice);
	}

	@Override
	public String toString() {
		return "MaintenanceReportDetail [MaintenanceReportID=" + maintenanceReport + ", ReplacementID=" + replacement
				+ ", comment=" + comment + ", Quantity=" + quantity + "]";
	}

}
