package model;

import java.sql.* ;
import javax.swing.* ;

/**
 * Date 30/05/2019
 * Il s'agit d'une classe qui faire la correspondance entre la base de donnee et le code
 * @author Pavilion
 *
 */
public class ConnexionMySql {
	Connection conn = null ;
	public static Connection ConnexionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GESTIONCLUB","root","");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null ;
		}
	}
}
