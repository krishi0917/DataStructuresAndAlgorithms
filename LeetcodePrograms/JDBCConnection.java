package LeetcodePrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public JDBCConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");
			st = con.createStatement();
			rs = st.executeQuery("sql");
		} catch (Exception ex) {
			System.out.println("error");
		} finally {
			con.close();
		}

	}
}


