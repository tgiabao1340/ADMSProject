package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Supplier {

	@Id
	@Column(name = "SupplierID")
	private String supplierID;
	@Column(name = "Country", columnDefinition = "nvarchar(30)")
	private String country;
	@Column(name = "SupplierName", columnDefinition = "nvarchar(50)")
	private String supplierName;
	@Column(name = "Address", columnDefinition = "nvarchar(50)")
	private String address;
	@Column(name = "PhoneNumber")
	private String phoneNumber;
	@Column(name = "TaxCode")
	private String taxCode;

	public Supplier() {
		super();
	}

	public Supplier(String country, String supperName, String address, String phoneNumber, String taxCode) {
		super();
		this.country = country;
		this.supplierName = supperName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.taxCode = taxCode;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((supplierID == null) ? 0 : supplierID.hashCode());
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
		Supplier other = (Supplier) obj;
		if (supplierID == null) {
			if (other.supplierID != null)
				return false;
		} else if (!supplierID.equals(other.supplierID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierID + ", country=" + country + ", supperName=" + supplierName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}
