package projetjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private String url = "jdbc:postgresql://localhost:5432/tppca";
	private String username = "postgres";
	private String password = "aze";
	private static Connexion instance = null;
	private static Connection cnx;

	public static Connexion getInstance() {
		if (instance == null) {
			instance = new Connexion();
		}
		return instance;
	}

	private Connexion() {
		this.connect();
	}

	public void connect() {

		try {
			Class.forName("org.postgresql.Driver");
			cnx = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCnx() {

		return cnx;
	}

	public void disconnect() {
		try {
			if (cnx != null)
				cnx.close();
		} catch (Exception e) {
		}
	}
}
