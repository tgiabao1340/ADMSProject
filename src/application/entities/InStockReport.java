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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class InStockReport {
	@Column(name = "Date")
	private LocalDate date;
	@Id
	@GenericGenerator(name = "sequence_rp_id", strategy = "application.entities.InStockReportGen")
	@GeneratedValue(generator = "sequence_rp_id")
	@Column(name = "InStockReportID")
	private String inStockReportID;
	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private Employee employee;

	@OneToMany(mappedBy = "inStockReport")
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	private List<InStockReportDetail> details;

	public InStockReport() {
		super();
	}

	public InStockReport(LocalDate date) {
		super();
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInStockReportID() {
		return inStockReportID;
	}

	public void setInStockReportID(String inStockReportID) {
		this.inStockReportID = inStockReportID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inStockReportID == null) ? 0 : inStockReportID.hashCode());
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
		InStockReport other = (InStockReport) obj;
		if (inStockReportID == null) {
			if (other.inStockReportID != null)
				return false;
		} else if (!inStockReportID.equals(other.inStockReportID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InStockReport [date=" + date + ", InStockReportID=" + inStockReportID + "]";
	}

}
