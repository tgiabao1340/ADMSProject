package application.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "CustomerID", referencedColumnName = "BussinessID")
public class Customer extends Person {

	@Column(name = "CreatedDate")
	private LocalDate createdDate;
	@Column(name = "IDCard")
	private String idCard;
	@Column(name = "IDCardDate")
	private LocalDate idCardDate;
	
	@Column(name ="PermanentAddress", columnDefinition = "nvarchar(20)")
	private String permanentAddress;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String address, boolean gender, LocalDate dateOfBirth,
			String phoneNumber, LocalDate createdDate, String idCard, LocalDate idCardDate,String permanentAddress) {
		super(firstName, lastName, address, gender, dateOfBirth, phoneNumber);
		this.createdDate = createdDate;
		this.idCard = idCard;
		this.idCardDate = idCardDate;
		this.permanentAddress = permanentAddress;
	}
	public Customer(String firstName, String lastName, String address, boolean gender, LocalDate dateOfBirth,
			String phoneNumber, LocalDate createdDate, String idCard, LocalDate idCardDate) {
		super(firstName, lastName, address, gender, dateOfBirth, phoneNumber);
		this.createdDate = createdDate;
		this.idCard = idCard;
		this.idCardDate = idCardDate;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public LocalDate getIdCardDate() {
		return idCardDate;
	}

	public void setIdCardDate(LocalDate idCardDate) {
		this.idCardDate = idCardDate;
	}

	@Override
	public String toString() {
		return "Customer [createdDate=" + createdDate + ", idCard=" + idCard + ", idCardDate=" + idCardDate
				+ ", bussinessID=" + bussinessID + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber
				+"ID adress = "+permanentAddress+ "]";
	}

}
