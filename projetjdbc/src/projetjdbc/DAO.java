package projetjdbc;

import java.util.ArrayList;

public interface DAO {
	public int insererClient(Client c);
	public int updateClient(Client c);
	public int deleteClient(int id);
	public ArrayList<Client> tousClients();
	public Client rechercherIdClient(int id);
}
