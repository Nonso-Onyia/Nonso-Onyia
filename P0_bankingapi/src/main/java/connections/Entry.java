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
        Connection connection = ConnectionUtil.getConnection();
        ClientController.init(app);
        AccountController.init(app);
		Connection connections = ConnectionUtil.getConnection();
		
		String sqlStatement = "SELECT * FROM Clients";
		try {
			PreparedStatement statement = connections.prepareStatement(sqlStatement);
			
			ResultSet resultSet = statement.executeQuery("select * from p0database-2.Client");
			
            System.out.println("================ test_table =================");
            while(resultSet.next()) {
                System.out.println("name: ["
                        + resultSet.getString("name")
                        + "]   clientID: ["
                        + resultSet.getInt("clientID")
                        + "]");
            }
            System.out.println("=============== /test_table =================");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}