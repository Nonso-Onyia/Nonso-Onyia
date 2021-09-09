package models;

import java.util.Objects;

public class Account {
	private int accountID;
	private int clientID;
	private float balance;
	private String name;
	
	public Account() {
		super();
	}
	
	public Account(int accountID, int clientID, float balance, String name) {
		super();
		this.accountID = accountID;
		this.clientID = clientID;
		this.balance = balance;
		this.name = name;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountID, balance, clientID, name);
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
		return accountID == other.accountID && Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance)
				&& clientID == other.clientID;
	}
	
	@Override
	public String toString() {
		return "Accounts [accountID=" + accountID + ", clientID=" + clientID + ", balance=" + balance + "]";
	}
}