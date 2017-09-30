package projetjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO implements DAO{
	private Connexion cnx ;
	private Statement st;
	
	public ClientDAO(){
		cnx = Connexion.getInstance();
		try {
			st = cnx.getCnx().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insererClient(Client c){
		int res = 0;
		try{
			String req = "INSERT INTO client VALUES (?,?)";
			Connection cn= cnx.getCnx();
			PreparedStatement ps = cn.prepareStatement(req);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNom());
			
			res = ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.disconnect();
		}
		return res;
	}
	public int updateClient(Client c){
		int res = 0;
		try{
			try {
				st = cnx.getCnx().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int id = c.getId();
			String nom = c.getNom();
			String prenom = c.getPrenom();
			String adresse = c.getAdresse();
			int telephone = c.getTelephone();
			String req ="UPDATE client SET nom='"+nom+"' WHERE id="+id; 
			res = st.executeUpdate(req);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.disconnect();
		}
		return res;
	}
	
	public int deleteClient(int id){
		int res = 0;
		try{
			try {
				st = cnx.getCnx().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			String req ="DELETE FROM client WHERE id="+id; 
			res = st.executeUpdate(req);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.disconnect();
		}
		return res;
	}
	
	public ArrayList<Client> tousClients(){
		ArrayList<Client> lstClients = null;
		try{
			try {
				st = cnx.getCnx().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String req ="SELECT * FROM client"; 
			ResultSet res = st.executeQuery(req);
			lstClients = new ArrayList<>();
			while(res.next()){
				int id = res.getInt("id");
				String nom = res.getString("nom");
//				String prenom = res.getString("prenom");
//				String adresse = res.getString("adresse");
//				int telephone = res.getInt("telephone");
				Client c = new Client(1, nom);
				lstClients.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			cnx.disconnect();
		}
		return lstClients;
	}
	
	public Client rechercherIdClient(int id){
		Client c = null;
		try{
			try {
				st = cnx.getCnx().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String req ="SELECT * FROM client WHERE id="+id; 
			ResultSet res = st.executeQuery(req);
			if(res.last()){
				//res.last();
				c = new Client(res.getInt(0),res.getString(1));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.disconnect();
		}
		return c;
	}
}
