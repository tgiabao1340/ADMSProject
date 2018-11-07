package application.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class MaintenanceReportDetailPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maintenanceReport;
	private String replacement;
	
	public MaintenanceReportDetailPK() {
		super();
	}

	public MaintenanceReportDetailPK(String maintenanceReport, String replacement) {
		super();
		this.maintenanceReport = maintenanceReport;
		this.replacement = replacement;
	}

	public String getMaintenanceReport() {
		return maintenanceReport;
	}

	public void setMaintenanceReport(String maintenanceReport) {
		this.maintenanceReport = maintenanceReport;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maintenanceReport == null) ? 0 : maintenanceReport.hashCode());
		result = prime * result + ((replacement == null) ? 0 : replacement.hashCode());
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
		MaintenanceReportDetailPK other = (MaintenanceReportDetailPK) obj;
		if (maintenanceReport == null) {
			if (other.maintenanceReport != null)
				return false;
		} else if (!maintenanceReport.equals(other.maintenanceReport))
			return false;
		if (replacement == null) {
			if (other.replacement != null)
				return false;
		} else if (!replacement.equals(other.replacement))
			return false;
		return true;
	}
	
	
}
