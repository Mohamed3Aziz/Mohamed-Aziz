package model;

import view.Authentification;

/**
 * Date 30/05/2019
 * Classe Proxy pour changer l'objet cible sans changer l'objet source (Pattern PROXY)
 * @author Pavilion
 */
public class Proxy extends Authentification{
	public Authentification window ;
	public void lancer() {
		try {
			window = new Authentification();
			window.init(); 
		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}
}
