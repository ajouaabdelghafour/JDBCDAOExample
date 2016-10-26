package projetjdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;

import java.sql.PreparedStatement;

public class CommandeDAO {
	private Connexion cnx ;
	private PreparedStatement st;
	
	public CommandeDAO(){
		cnx = new Connexion();
	}
	
	public int insererCommande(Commande c){
		int res = 0;
		try{
			String req = "INSERT INTO commande VALUES (?,?,?)";
			st = cnx.getCnx().prepareStatement(req);
			st.setInt(1, c.getId());
			st.setInt(3, c.getClient().getId());
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			c.setDate(Date.valueOf(d.format(c.getDate()).toString()));
			st.setDate(2, (Date) c.getDate());
			res = st.executeUpdate();
		}catch(Exception e){
		  e.printStackTrace();
		}
		finally{
			cnx.fermer();
		}
		return res;
	}
	
	public int updateCommande(Commande c){
		int res = 0;
		try{
			int id = c.getId();
			int idClient = c.getClient().getId();
			float pttc = c.getPttc();
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			c.setDate(Date.valueOf(d.format(c.getDate()).toString()));
			st.setDate(4, (Date) c.getDate());
			String req ="UPDATE commande SET idClient=?,date=?,pttc=? WHERE id=?"; 
			st = cnx.getCnx().prepareStatement(req);
			st.setInt(1, idClient);
			st.setDate(2, (Date) c.getDate());
			st.setFloat(3, pttc);
			st.setInt(4, id);
			res = st.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.fermer();
		}
		return res;
	}
	
	public int deleteCommande(int id){
		int res = 0;
		try{
			String req ="DELETE FROM commande WHERE id=?"; 
			st = cnx.getCnx().prepareStatement(req);
			st.setInt(1, id);
			res = st.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			cnx.fermer();
		}
		return res;
	}
	
	public ArrayList<Commande> tousCommandes(){
		ArrayList<Commande> lstCommandes = null;
		try{
			Statement st = cnx.getConnexion();
			String req ="SELECT * FROM commande"; 
			ResultSet res = st.executeQuery(req);
			lstCommandes = new ArrayList<>();
			while(res.next()){
				while(res.next()){
					int id = res.getInt("id");
					int idClient = res.getInt("idClient");
					Date date = res.getDate("Date");
					float pttc = res.getFloat("pttc");
					ClientDAO daocli = new ClientDAO();
					Client client = daocli.rechercherIdClient(idClient);
					Commande c = new Commande(id, client, pttc, date);
					lstCommandes.add(c);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			cnx.fermer();
		}
		return lstCommandes;
	}
	
	public ArrayList<Commande> rechercheCommandeClient(int idClient){
		ArrayList<Commande> lstCommandes = null;
		try{
			String req ="SELECT * FROM commande WHERE idClient = ?"; 
			st = cnx.getCnx().prepareStatement(req);
			st.setInt(1, idClient);
			ResultSet res = st.executeQuery();
			lstCommandes = new ArrayList<>();
			while(res.next()){
				int id = res.getInt("id");
				Date date = res.getDate("Date");
				float pttc = res.getFloat("pttc");
				ClientDAO daocli = new ClientDAO();
				Client client = daocli.rechercherIdClient(idClient);
				Commande c = new Commande(id, client, pttc, date);
				lstCommandes.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			cnx.fermer();
		}
		return lstCommandes;
	}
	
	public ArrayList<Commande> rechercheCommande(java.util.Date date){
		ArrayList<Commande> lstCommandes = null;
		try{
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			date = Date.valueOf(d.format(date).toString());
			String req ="SELECT * FROM 'Commande' WHERE date = ?"; 
			st = cnx.getCnx().prepareStatement(req);
			st.setDate(1, (Date) date);
			ResultSet res = st.executeQuery();
			lstCommandes = new ArrayList<>();
			while(res.next()){
				int id = res.getInt("id");
				int idClient = res.getInt("idClient");
				float pttc = res.getFloat("pttc");
				ClientDAO daocli = new ClientDAO();
				Client client = daocli.rechercherIdClient(idClient);
				Commande c = new Commande(id, client, pttc, date);
				lstCommandes.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			cnx.fermer();
		}
		return lstCommandes;
	}
}
