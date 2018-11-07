package application.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	/**
	 * 
	 */
	@Id
	@Column(name = "AccountID")
	private String accountID;
	@Column(name = "Password")
	private String password;
	@Column(name = "CreatedDate")
	private LocalDate createdDate;

	public Account(String accountID, String password, LocalDate createdDate) {
		super();
		this.accountID = accountID;
		this.password = password;
		this.createdDate = createdDate;
	}

	public Account(String password, LocalDate createdDate) {
		super();
		this.password = password;
		this.createdDate = createdDate;
	}

	public Account(String accountID, String password) {
		this.accountID = accountID;
		this.password = password;
	}

	public Account() {
		super();
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountID == null) ? 0 : accountID.hashCode());
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
		Account other = (Account) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", password=" + password + ", createdDate=" + createdDate + "]";
	}

}
