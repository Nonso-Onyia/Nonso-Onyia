package connections;
import io.javalin.Javalin;
import controllers.ClientController;
import controllers.AccountController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.CDimp;
import dao.ADimp;
import java.util.Random;
public class Entry{
	public static void main(String[] args) {
		
		Javalin app = Javalin.create()
        		.start(7000);
		
		app.get("/Client", ClientController::getAllClients);
		app.post("/Client/:name", ClientController::insertClient);
		app.get("/Client/:id", ClientController::getClientByID);
		app.put("/Client/:id/:name", ClientController::updateClient);
		app.delete("/Client/:id", ClientController::deleteClient);
		
		/* app.post("/client/:clientID/accounts/:accountID"),
				AccountController::insertAccountByID);
				*/
		
		app.get("/client/:clientID/accounts", 
				AccountController::getAllClientAccounts);
		
		app.post("/client/:clientID/accounts/:accountID", 
				AccountController::getClientAccountByID);
		
		app.get("/client/:clientID/accounts/"
				+ "?amountLessThan=:amount", 
				AccountController::getClientAccountLessThan);
		app.get("/client/:clientID/accounts/"
				+ "?amountGreaterThan", 
				AccountController::getClientAccountGreaterThan);
		app.get("/client/:clientID/accounts/"
				+ "?amountLessThan=:maxamount"
				+ "&amountGreaterThan=:minamount", 
				AccountController::getClientAccountRange);
		app.patch("/client/:clientID/accounts/:accountID", 
				AccountController::depositOrWithdraw);
		
		app.patch("/clientF/:clientFrom/clientT/:clientTo"
				+ "accounts/:accountFrom"
				+ "/transfer/:accountTo", 
				AccountController::transferAccount);
		
		app.post("/client/:clientID/accounts/:balance", 
				AccountController::insertAccount);
		
		app.put("/client/:clientID/accounts/:accountID",
				AccountController::updateAccount);
		app.delete("/client/:clientID/accounts/:accountID", 
				AccountController::deleteAccount);
	}
}