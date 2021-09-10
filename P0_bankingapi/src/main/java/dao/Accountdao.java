package dao;
import java.util.List;
import models.Account;

public interface Accountdao {
	public List<Account> getAllClientAccounts(int clientID, int accountID, String name, float balance);
	public Account getClientAccountByID(int clientID, int accountID);
	public List<Account> getClientAccountsByMinAmt(int clientID, float amount);
	public List<Account> getClientAccountsByMaxAmt(int clientID, float amount);
	public List<Account> getClientAccountsByRange(int clientID, float minAmount, float maxAmount);
	public void createAccount(int clientID, float balance);
	public void deleteAccount(int accountID);
	public void deposit(int accountID, float balance);
	public void withdraw(int accountID, float balance);
	public void update(int accountID, float amount);
	
}
