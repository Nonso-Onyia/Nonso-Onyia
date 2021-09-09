package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
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
        app.put("/clients/:id", ClientController::updateClient);
		
		app.delete("/client/:id", ClientController::deleteClient);
	}
	public static void getAllClients(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		ctx.json(dao.getAllClients());
		ctx.status(200);
	}
	public static void getClientByID(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		Integer id = Integer.parseInt(ctx.pathParam(":id"));
		if (!Validator.ifClientExists(dao, id)) {
			ctx.status(400);
			ctx.result("No such client found.");
		}
		ctx.json(dao.getClienttByID(id));
	}
	
	public static void insertClient(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		String name = ctx.pathParam(":name");
		dao.createClient(name);
		dao.getClientByName(name);
		ctx.status(201);
		/*ctx.result("No such client found.");
		 * 
		 */
	}
	public static void deleteClient(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		Integer id = Integer.parseInt(ctx.pathParam(":id"));
		if (!Validator.ifClientExists(dao, id)) {
			ctx.status(400);
			ctx.result("No such client found.");
		}
		dao.deleteClient(id);
	}
	public static void updateClient(Context ctx) {
		CDimp dao = new CDimp(ConnectionUtil.getConnection());
		Integer id = Integer.parseInt(ctx.pathParam("id"));
		String name = ctx.pathParam("name");
		if (!Validator.ifClientExists(dao, id)) {
			ctx.status(400);
			ctx.result("No such client found.");
		}
		else {
			dao.updateClient(id, name);
		}

}
}
