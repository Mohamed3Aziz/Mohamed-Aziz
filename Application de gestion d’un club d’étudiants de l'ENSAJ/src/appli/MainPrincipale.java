package appli;

import java.awt.EventQueue;

import model.Proxy;

/**
 * Date 31/05/2019
 * Classe pricipale qui fait appel au d'autres classes
 * @author Pavilion
 *
 */
public class MainPrincipale {

	public static void main(String[] args) {		
		Proxy window = new Proxy();
		window.lancer(); 
	}
}