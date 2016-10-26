package projetjdbc;

import java.util.Date;

public class Commande {
	
	private int id;
	private Client client;
	private float pttc;
	private Date date;
	
	public Commande(int id, Client client, float pttc, Date date) {
		this.id = id;
		this.client = client;
		this.pttc = pttc;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public float getPttc() {
		return pttc;
	}
	public void setPttc(float pttc) {
		this.pttc = pttc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", idclient=" + client + ", pttc=" + pttc + ", date=" + date + "]";
	}
	
}
