package application.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

	@Id
	@GenericGenerator(name = "sequence_ps_id", strategy = "application.entities.PersonGen")
	@GeneratedValue(generator = "sequence_ps_id")
	@Column(name = "BussinessID")
	protected String bussinessID;
	@Column(name = "FirstName", columnDefinition = "nvarchar(50)")
	protected String firstName;
	@Column(name = "LastName", columnDefinition = "nvarchar(50)")
	protected String lastName;
	@Column(name = "Address", columnDefinition = "nvarchar(100)")
	protected String address;
	@Column(name = "Gender")
	protected boolean gender;
	@Column(name = "DateOfBirth")
	protected LocalDate dateOfBirth;
	@Column(name = "PhoneNumber")
	protected String phoneNumber;

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String address, boolean gender, LocalDate dateOfBirth,
			String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
	}

	public String getBussinessID() {
		return bussinessID;
	}

	public void setBussinessID(String bussinessID) {
		this.bussinessID = bussinessID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean b) {
		this.gender = b;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bussinessID == null) ? 0 : bussinessID.hashCode());
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
		Person other = (Person) obj;
		if (bussinessID == null) {
			if (other.bussinessID != null)
				return false;
		} else if (!bussinessID.equals(other.bussinessID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [bussinessID=" + bussinessID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
