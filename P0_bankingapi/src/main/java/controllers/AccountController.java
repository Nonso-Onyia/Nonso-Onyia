package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Client;
import models.Account;
import dao.ADimp;
import dao.CDimp;
import connections.ConnectionUtil;
import services.Validator;


public class AccountController {
	
	private static Javalin javalin;
	
	public static void init(Javalin app) {
		javalin = app;
		app.get("/client/:clientID/accounts", 
				AccountController::getAllClientAccounts);
		app.get("/client/:clientID/accounts/:accountID", 
				AccountController::getClientAccountByID);
		app.get("/client/:clientID/accounts/"
				+ "?amountLessThan=:amount", 
				AccountController::getClientAccountLessThan);
		app.get("/client/:clientID/accounts/"
				+ "?amountGreaterThan=:amount", 
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
	public static void getAllClientAccounts(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		ctx.json(dao.getAllClientAccounts());
		ctx.status(200);
		
	}
	
	public static void getClientAccountByID(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		int accountID = Integer.parseInt(ctx.pathParam("accountID"));
		if (!Validator.ifAccountExists(dao, clientID, accountID)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		ctx.json(dao.getClientAccountByID(clientID, accountID));
	}
	
	public static void getClientAccountLessThan(Context ctx) {
		ADimp accountDAO = new ADimp(ConnectionUtil.getConnection());
		CDimp clientDAO = new CDimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		int amount = Integer.parseInt(ctx.pathParam("amount"));
		if (!Validator.ifClientExists(clientDAO, clientID)) {
			ctx.status(404);
			ctx.result("No such client exists");
		}
		ctx.json(accountDAO.getClientAccountsByMinAmt(clientID, amount));
	}
	public static void getClientAccountGreaterThan(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		CDimp clientDAO = new CDimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		int amount = Integer.parseInt(ctx.pathParam("amount"));
		if (!Validator.ifClientExists(clientDAO, clientID)) {
			ctx.status(404);
			ctx.result("No such client exists");
		}
		ctx.json(dao.getClientAccountsByMinAmt(clientID, amount));
	}
	public static void getClientAccountRange(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		CDimp clientDAO = new CDimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		int minAmount = Integer.parseInt(ctx.pathParam("minAmount"));
		int maxAmount = Integer.parseInt(ctx.pathParam("maxAmount"));
		if (!Validator.ifClientExists(clientDAO, clientID)) {
			ctx.status(404);
			ctx.result("No such client exists");
		}
		ctx.json(dao.getClientAccountsByRange(clientID, minAmount, maxAmount));
	}
	public static void insertAccount(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		float balance = Float.parseFloat(ctx.pathParam("balance"));
		dao.createAccount(clientID, balance);
		ctx.status(201);
	}
	public static void updateAccount(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		int accountID = Integer.parseInt(ctx.pathParam("accountID"));
		int amount = Integer.parseInt(ctx.pathParam("amount"));
		if (!Validator.ifAccountExists(dao, clientID, accountID)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		dao.update(clientID, amount);
	}
	public static void deleteAccount(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		int accountID = Integer.parseInt(ctx.pathParam("accountID"));
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		if (!Validator.ifAccountExists(dao, clientID, accountID)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		dao.deleteAccount(accountID);
	}
	public static void depositOrWithdraw(Context ctx) {
		ADimp dao = new ADimp(ConnectionUtil.getConnection());
		int accountID = Integer.parseInt(ctx.pathParam("accountID"));
		int clientID = Integer.parseInt(ctx.pathParam("clientID"));
		
		if (!Validator.ifAccountExists(dao, clientID, accountID)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		String[] jsonObject = jsonParser(ctx.body());
		if (jsonObject[0].equals("deposit")) {
			dao.deposit(accountID, Float.parseFloat(jsonObject[1]));
		}
		else if (jsonObject[0].equals("withdraw")) {
			Account account = ctx.bodyAsClass(Account.class);
			float balance = account.getBalance();
			if (Validator.ifInsufficientFunds(balance, Float.parseFloat(jsonObject[1]))) {
				ctx.status(422);
				ctx.result("Insufficient funds");
			}
			else {
				dao.withdraw(accountID, Float.parseFloat(jsonObject[1]));
			}
		}
	}
	public static void transferAccount(Context ctx) {
		ADimp accountDAO = new ADimp(ConnectionUtil.getConnection());
		CDimp clientDAO = new CDimp(ConnectionUtil.getConnection());
		int clientFrom = Integer.parseInt(ctx.pathParam("clientFrom"));
		int clientTo = Integer.parseInt(ctx.pathParam("clientTo"));
		int accountFrom = Integer.parseInt(ctx.pathParam("accountFrom"));
		int accountTo = Integer.parseInt(ctx.pathParam("accountTo"));
		
		if (!Validator.ifClientExists(clientDAO, clientFrom)) {
			ctx.status(404);
			ctx.result("No such client exists");
		}
		if (!Validator.ifAccountExists(accountDAO, clientFrom, accountFrom)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		if (!Validator.ifAccountExists(accountDAO, clientTo, accountTo)) {
			ctx.status(404);
			ctx.result("No such account exists");
		}
		String[] jsonObject = jsonParser(ctx.body());
		if (!Validator.ifInsufficientFunds(Float.parseFloat(jsonObject[1]), Float.parseFloat(jsonObject[1]))) {
			ctx.status(422);
			ctx.result("Insufficient funds");
		}
		else { 
			accountDAO.withdraw(accountFrom, Float.parseFloat(jsonObject[1]));
			accountDAO.deposit(accountTo, Float.parseFloat(jsonObject[1]));
		}
	}
	public static String[] jsonParser(String json) {
		String[] finishedJSON = new String[2];
		String[] parser = json.split("\"");
		finishedJSON[0] = parser[1];
		parser = parser[2].split(":");
		parser = parser[1].split("}");
		finishedJSON[1] = parser[0];
		return finishedJSON;
	}

}
