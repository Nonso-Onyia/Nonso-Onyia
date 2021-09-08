package services;

import dao.ADimp;
import dao.CDimp;

public class Validator {
	public static boolean ifClientExists(CDimp client, int id) {
		if (client.getClienttByID(id) == null) {
			return false;
		}
		
		return true;
	}
	
	public static boolean ifAccountExists(ADimp account, int clientID, int accountID) {
		if (account.getClientAccountByID(clientID, accountID) == null) {
			return false;
		}
		
		return true;
	}
	
	public static boolean ifInsufficientFunds(float balance, float amount) {
		if ((balance - amount) < 0) {
			return true;
		}
		
		return false;
	}
}