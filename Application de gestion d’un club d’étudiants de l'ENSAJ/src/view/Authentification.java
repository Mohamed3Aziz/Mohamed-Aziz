package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import model.ConnexionMySql;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date 30/05/2019
 * Il s'agit d'une interface graphique Authentification
 * @author Pavilion
 * @version 1.0
 */

public class Authentification extends JFrame {

	/**
	 * Bibliothèque graphique pour le langage de programmation Java
	 */
	private JFrame frame;
	private JTextField usernameField; 
	private JPasswordField passwordField;
	public JLabel lblUsername ;
	public JLabel lblLensaj ;
	public JLabel lblDtudiantsDe ;
	public JLabel lblGestionDunClub;
	int posX=0,posY=0;
	Connection cnx = null ;  
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	/**
	 * Fermer l'application en cliquant sur X
	 */
	void fermer() {
		frame.dispose();
	}

	/**
	 * Launch the application.
	 */
	private static Point point = new Point();
	public void init() {		
		Authentification window = new Authentification();
		window.frame.setUndecorated(true);
		window.frame.setVisible(true);		
	}

	/**
	 * Creation de l'application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialiser le contenu du cadre.
	 */
	
	
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 625, 420);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		cnx = ConnexionMySql.ConnexionDB(); 
		
		lblGestionDunClub = new JLabel("Gestion d\u2019un club ");
		lblGestionDunClub.setForeground(Color.WHITE);
		lblGestionDunClub.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDunClub.setBounds(31, 224, 238, 51);
		frame.getContentPane().add(lblGestionDunClub);
		
		lblDtudiantsDe = new JLabel("d\u2019\u00E9tudiants de");
		lblDtudiantsDe.setForeground(Color.WHITE);
		lblDtudiantsDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDtudiantsDe.setBounds(56, 264, 193, 51);
		frame.getContentPane().add(lblDtudiantsDe);
		
		lblLensaj = new JLabel("L'ENSAJ");
		lblLensaj.setForeground(Color.WHITE);
		lblLensaj.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLensaj.setBounds(84, 308, 119, 51);
		frame.getContentPane().add(lblLensaj);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setBounds(303, 73, 93, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setBounds(303, 161, 93, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(303, 186, 283, 34);
		frame.getContentPane().add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setForeground(Color.BLACK);
		usernameField.setBackground(Color.WHITE);
		usernameField.setColumns(10);
		usernameField.setBounds(303, 101, 283, 34);
		frame.getContentPane().add(usernameField);
		
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setBackground(Color.RED);
		btnSeConnecter.setForeground(Color.WHITE);
		btnSeConnecter.addActionListener(/**
		 * @author Pavilion
		 * Authentification de l'utilisateur dans l'interface
		 */
		new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText().toString();
				String password = passwordField.getText().toString();
				String role = "Administrateur";
				String role2 = "Secrétaire général";
				String role3 = "Trésorier";
				String role4 = "Membre Personnel";
				String sql = "select username,password,Role from utilisateurs" ;
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					int i=0 ;
					if (username.equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(null,"Remplissez les champs Vides ! ");
					}
					else {
					while(resultat.next())
					{
						String username1 = resultat.getString("username");
						String password1 = resultat.getString("password");
						String role1 = resultat.getString("Role");
						if (username1.equals(username) && password1.equals(password) && role1.equals(role)) {
							JOptionPane.showMessageDialog(null,"Connexion Resussi");
							i=1;
							MenuAdministrateur obj = new MenuAdministrateur();
							obj.setPreferredSize(new Dimension(625, 420));
							obj.setUndecorated(true);	    
							obj.pack();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							fermer();
						}
						if (username1.equals(username) && password1.equals(password) && role1.equals(role2)) {
							JOptionPane.showMessageDialog(null,"Connexion Resussi");
							i=1;
							MenuSecretaire obj = new MenuSecretaire();
							obj.setPreferredSize(new Dimension(625, 420));
							obj.setUndecorated(true);	    
							obj.pack();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							fermer();
						}
						if (username1.equals(username) && password1.equals(password) && role1.equals(role3)) {
							JOptionPane.showMessageDialog(null,"Connexion Resussi");
							i=1;
							MenuTresorie obj = new MenuTresorie();
							obj.setPreferredSize(new Dimension(625, 420));
							obj.setUndecorated(true);	    
							obj.pack();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							fermer();
						}
						if (username1.equals(username) && password1.equals(password) && role1.equals(role4))  {
							JOptionPane.showMessageDialog(null,"Connexion Resussi");
							i=1;
							MenuUser obj = new MenuUser();
							obj.setPreferredSize(new Dimension(625, 420));
							obj.setUndecorated(true);	    
							obj.pack();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							fermer();
						}
						
					}
					if(i==0) {
						JOptionPane.showMessageDialog(null,"Connexion Echouee");
					}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		});
		btnSeConnecter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeConnecter.setBounds(303, 264, 283, 34);
		frame.getContentPane().add(btnSeConnecter);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Authentification.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 215);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Authentification.class.getResource("/img/archi_urba_deco_background.jpg")));
		lblNewLabel.setBounds(0, 0, 283, 461);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);			}
		});
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setForeground(Color.RED);
		lbl_close.setBackground(Color.RED);
		lbl_close.setBounds(611, 0, 17, 14);
		frame.getContentPane().add(lbl_close);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe oubli\u00E9 ?");
		lblMotDePasse.addMouseListener(/**
		 * @author Pavilion
		 * Interface Mot de passe oubliee
		 */
		new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) {
				IndicPassword obj = new IndicPassword();
				obj.setPreferredSize(new Dimension(417, 240));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblMotDePasse.setForeground(Color.BLUE);
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMotDePasse.setBounds(490, 223, 97, 14);
		frame.getContentPane().add(lblMotDePasse);
		frame.addMouseListener(/**
		 * @author Pavilion
		 * Declpacer L'interface graphique
		 */
		new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        point.x = e.getX();
		        point.y = e.getY();
		      }
		    });
		    frame.addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		        Point p = frame.getLocation();
		        frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
		      }
		    });
	}
}
