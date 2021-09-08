package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Client;
import models.Account;
import dao.ADimp;
import dao.CDimp;
import connections.ConnectionUtil;
import services.Validator;

public class ClientController {
	
	private static Javalin javalin;
	
	public static void init(Javalin app) {
		javalin = app;
		app.get("/client", ClientController::getAllClients);
		app.get("/client/:id", ClientController::getClientByID);
		
        app.post("/client/:name", ClientController::insertClient);
		
		app.delete("/client/:id", ClientController::deleteClient);
	}
	public static void getAllClients(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		ctx.json(dao.getAllClients());
		ctx.status(200);
	}
	public static void getClientByID(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		Integer id = Integer.parseInt(ctx.pathParam("id"));
		if (!Validator.ifClientExists(dao, id)) {
			ctx.status(400);
			ctx.result("No such client found.");
		}
		
		ctx.json(dao.getClienttByID(id));
	}
	
	public static void insertClient(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		String name = ctx.pathParam("name");
		dao.createClient(name);
		ctx.status(201);
	}
	public static void deleteClient(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		Integer id = Integer.parseInt(ctx.pathParam("id"));
		if (!Validator.ifClientExists(dao, id)) {
			ctx.status(400);
			ctx.result("No such client found.");
		}
		dao.deleteClient(id);
	}

}
