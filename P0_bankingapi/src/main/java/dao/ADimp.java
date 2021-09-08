package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;

public class ADimp implements Accountdao {
	
    Connection connection;
	
	public ADimp(Connection conn) {
		connection = conn;
	}
	@Override
	public List<Account> getAllClientAccounts(int clientID) {
		List<Account> accountList = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts"
				+ "WHERE ClientID = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, clientID);
			ResultSet resultSet = prepStatement.executeQuery();
			
            while(resultSet.next()) {
            	int accountID = resultSet.getInt("accountID");
            	int clientID2 = resultSet.getInt("clientID");
            	int balance = resultSet.getInt("balance");
            	
            	Account account = new Account(accountID, clientID2, balance);
            	accountList.add(account);
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}
	@Override
	public Account getClientAccountByID(int clientID, int accountID) {
		Account resultAccount = null;
		String sql = "SELECT * FROM accounts "
				+ "WHERE ClientID = ?"
				+ "AND AccountID = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, clientID);
			prepStatement.setInt(2, accountID);
			ResultSet resultSet = prepStatement.executeQuery();
			
            while(resultSet.next()) {
            	int accountID2 = resultSet.getInt("accountID");
            	int clientID2 = resultSet.getInt("clientID");
            	int balance = resultSet.getInt("balance");
            	
            	resultAccount = new Account(accountID2, clientID2, balance);
            }
            
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return resultAccount;
	}
	@Override
	public List<Account> getClientAccountsByMinAmt(int clientID, float amount){
		List<Account> accountList = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts "
				+ "WHERE ClientID = ? AND Balance > ?";
		
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, clientID);
			prepStatement.setFloat(2, amount);
			ResultSet resultSet = prepStatement.executeQuery();
			
            while(resultSet.next()) {
            	int accountID = resultSet.getInt("accountID");
            	int clientID2 = resultSet.getInt("clientID");
            	int balance = resultSet.getInt("balance");
            	
            	Account account = new Account(accountID, clientID2, balance);
            	accountList.add(account);
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}
	@Override
	public List<Account> getClientAccountsByMaxAmt(int clientID, float amount){
		List<Account> accountList = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts "
				+ "WHERE ClientID = ? AND Balance < ?";
		
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, clientID);
			prepStatement.setFloat(2, amount);
			ResultSet resultSet = prepStatement.executeQuery();
			
            while(resultSet.next()) {
            	int accountID = resultSet.getInt("accountID");
            	int clientID2 = resultSet.getInt("clientID");
            	int balance = resultSet.getInt("balance");
            	
            	Account account = new Account(accountID, clientID2, balance);
            	accountList.add(account);
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}
	@Override
	public List<Account> getClientAccountsByRange(int clientID, float minAmount, float maxAmount){
		List<Account> accountList = new ArrayList<Account>();
		String sql = "SELECT * FROM accounts "
				+ "WHERE ClientID = ? AND Balance > ? AND Balance < ?";
		
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, clientID);
			prepStatement.setFloat(2, minAmount);
			prepStatement.setFloat(3, maxAmount);
			ResultSet resultSet = prepStatement.executeQuery();
			
            while(resultSet.next()) {
            	int accountID = resultSet.getInt("accountID");
            	int clientID2 = resultSet.getInt("clientID");
            	int balance = resultSet.getInt("balance");
            	
            	Account account = new Account(accountID, clientID2, balance);
            	accountList.add(account);
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;
	}
	@Override
	public void createAccount(int clientID, float balance) {
		String sql = "INSERT INTO accounts (ClientID, Balance)"
				+ "VALUES (?, ?)";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, clientID);
            prepStatement.setFloat(2, balance);
            prepStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	@Override
	public void deleteAccount(int accountID) {
		String sql = "DELETE FROM accounts "
				+ "WHERE AccountID = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, accountID);
            prepStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deposit(int accountID, float balance) {
		String sql = "UPDATE accounts "
				+ "SET Balance = Balance + ?"
				+ "WHERE AccountID = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, accountID);
            prepStatement.setFloat(2, balance);
            prepStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void withdraw(int accountID, float balance) {
		String sql = "UPDATE accounts "
				+ "SET Balance = ?"
				+ "WHERE AccountID = ?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, accountID);
            float amount = 0;
			prepStatement.setFloat(2, amount);
            prepStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(int accountID, float amount) {
		// TODO Auto-generated method stub
		
	}

}
