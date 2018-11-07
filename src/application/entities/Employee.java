package application.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "EmployeeID", referencedColumnName = "BussinessID")
public class Employee extends Person {
	@Column(name = "Position", columnDefinition = "nvarchar(25)")
	private String position;
	@OneToOne
	@JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
	private Account account;

	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, String address, boolean gender, LocalDate dateOfBirth,
			String phoneNumber, String position, Account account) {
		super(firstName, lastName, address, gender, dateOfBirth, phoneNumber);
		this.position = position;
		this.account = account;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Employee [position=" + position + ", bussinessID=" + bussinessID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", phoneNumber=" + phoneNumber + "]";
	}
}
