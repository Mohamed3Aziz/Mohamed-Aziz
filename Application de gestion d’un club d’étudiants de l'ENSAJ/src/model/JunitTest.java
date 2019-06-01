package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;
import org.junit.Before;

import view.Authentification;
import view.MenuAdministrateur;
import view.MenuUser;


/**
 * Date 30/05/2019
 * Il s'agit d'une implementation des tests unitaires
 * @author Pavilion
 * @version 1.0
 *
 */
class JunitTest {
	
	private Authentification obj;
	private Authentification obj2;
	private MenuAdministrateur obj3;
	private MenuUser obj4;

	@Before
	public void mesInitialisations() {
		obj = new Authentification();
		obj2 = new Authentification();
		obj3 = new MenuAdministrateur();
		obj4 = new MenuUser();		
	}
	@Test
	void test() throws Exception {
		mesInitialisations();
		try {
			assertEquals("L'ENSAJ", obj.lblLensaj.getText());
			assertEquals("Username", obj.lblUsername.getText());
			assertEquals("Gestion d\u2019un club ", obj.lblGestionDunClub.getText());
			assertEquals( obj2.lblLensaj.getText(), obj.lblLensaj.getText());
			assertEquals( obj3.lblGestionDunClub.getText(), obj.lblGestionDunClub.getText());
			assertEquals( "Membre Personnel", obj4.lblMembrePersonnel.getText());
			assertNotNull(obj);
			assertTrue(!obj.equals(null));
			assertTrue(!obj.equals(obj2));
			assertFalse(obj.equals(obj3));
			assertNotSame(obj,obj2);
			assertNotSame(obj,obj3);
			assertNotSame(obj2,obj3);
		}
		catch(Exception e) {
			System.err.print("error");
		}
	}

}
