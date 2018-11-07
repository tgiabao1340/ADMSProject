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
public class MaintenanceReport {
	@Column(name = "Date")
	private LocalDate date;
	@Id
	@GenericGenerator(name = "sequence_rpm_id", strategy = "application.entities.MaintenanceReportGen")
	@GeneratedValue(generator = "sequence_rpm_id")
	@Column(name = "MaintenanceReportID")
	private String maintenanceReportID;
	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private Employee employee;
	@OneToMany(mappedBy = "maintenanceReport")
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	private List<MaintenanceReportDetail> details;

	public MaintenanceReport() {
		super();
	}

	public MaintenanceReport(LocalDate date, Employee employee, List<MaintenanceReportDetail> details) {
		super();
		this.date = date;
		this.employee = employee;
		this.details = details;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<MaintenanceReportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<MaintenanceReportDetail> details) {
		this.details = details;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getMaintenanceReportID() {
		return maintenanceReportID;
	}

	public void setMaintenanceReportID(String maintenanceReportID) {
		this.maintenanceReportID = maintenanceReportID;
	}

	public long getTotal() {
		long total = 0l;
		for (int i = 0; i < details.size(); i++) {
			total += details.get(i).getTotalRaw();
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maintenanceReportID == null) ? 0 : maintenanceReportID.hashCode());
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
		MaintenanceReport other = (MaintenanceReport) obj;
		if (maintenanceReportID == null) {
			if (other.maintenanceReportID != null)
				return false;
		} else if (!maintenanceReportID.equals(other.maintenanceReportID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaintenanceReport [Date=" + date + ", MaintenanceReportID=" + maintenanceReportID + "]";
	}

}
