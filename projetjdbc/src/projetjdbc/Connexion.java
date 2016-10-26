package projetjdbc;

import java.sql.*;
import java.util.Iterator;

public class Connexion {
	private String url = "jdbc:postgresql://localhost:5432/tppca";
	private String username = "postgres";
	private String password = "aze";
	Statement st = null;
	Connection cn;

	public Statement getConnexion() {

		try {
			Class.forName("org.postgresql.Driver");
			cn = DriverManager.getConnection(url, username, password);
			st = cn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	public Connection getCnx() {
		try {
			Class.forName("org.postgresql.Driver");
			cn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}

	public void fermer() {
		try {
			if (st != null)
				st.close();
			if (cn != null)
				cn.close();
		} catch (Exception e) {
		}
	}
}
