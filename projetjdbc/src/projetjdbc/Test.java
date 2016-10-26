package projetjdbc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


//Comment added
//Another comment added on Eclipse
public class Test {
	static ClientDAO manipuler = new ClientDAO();
	private static Client saisirClient(){
		Scanner sc = new Scanner(System.in);
		try{
			int id;
			do{
				System.out.println("Saisir id :");
				id = sc.nextInt();
			}while(manipuler.rechercherIdClient(id)!=null);
			System.out.println("Saisir nom :");
			String nom = sc.next();
			System.out.println("Saisir prénom :");
			String prenom = sc.next();
			System.out.println("Saisir adresse :");
			String adresse = sc.next();
			System.out.println("Saisir téléphone :");
			int telephone = sc.nextInt();
			return new Client(id, nom, prenom, adresse, telephone);
		}catch (Exception e) {
			System.out.println("Erreur de saisie !!!");
			saisirClient();
		}
		return null;
	}
	
	public static void afficherMenu(){
		System.out.println("______________MENU_____________");
		System.out.println("1:      Inserer un client");
		System.out.println("2:     Modifier un client");
		System.out.println("3:     Supprimer un client");
		System.out.println("4:     Rechercher un client");
		System.out.println("5:   Afficher tous les client");
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		afficherMenu();
		System.out.println("Saisir votre choix :");
		int choix = sc.nextInt();
		switch(choix){
			case 1 :Client c = saisirClient();
					if(c!=null){
						int e= manipuler.insererClient(c);
					if(e>0)
						System.out.println("Client ajouté ");
					else
						System.out.println("Erreur !!!");
					}
					break;
			case 2 :c = saisirClient();
					if(c!=null){
					int e= manipuler.insererClient(c);
					if(e>0)
						System.out.println("Client ajouté ");
					else
						System.out.println("Erreur !!!");
					}
					break;
			case 3 :System.out.println("saisir ID :");
					int id = sc.nextInt();
					manipuler.deleteClient(id);
					break;
			case 4 :System.out.println("saisir ID :");
					id = sc.nextInt();
					System.out.println(manipuler.rechercherIdClient(id).toString());
					break;
			case 5 :System.out.println("Tous les clients :");
					ArrayList<Client> lst = manipuler.tousClients();
					for(Client cli : lst)
						System.out.println(cli.toString());
					break;
			default : System.out.println("Choix invalide !!!");
		}
		
		CommandeDAO cmd = new CommandeDAO();
		//Date date = new Date(2016, 5, 3);
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 4, 3);
		Client c=new Client(5, "Ali");
		cmd.insererCommande(new Commande(1000, c, 545, cal.getTime()));
		
		ArrayList<Commande> lst = cmd.rechercheCommandeClient(222);
		if(lst!=null)
			for(Commande cm : lst)
				System.out.println(cm.toString());
		ArrayList<Commande> lstD = cmd.rechercheCommande(cal.getTime());
		if(lstD!=null)
			for(Commande cl : lstD)
				System.out.println(cl.toString());
		
		
	}

}
