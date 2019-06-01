package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.ConnexionMySql;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

/**
 * Date 30/05/2019
 * Il s'agit d'une interface graphique qui permet la recuperation du mot de passe oublie 
 * @author Pavilion
 *
 */
public class IndicPassword extends JFrame{

	private JFrame frame;
	Connection cnx = null ;
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	private JLabel lblNewLabel;
	private JLabel lblMotDePasse;
	private JLabel lblVousInquieter;
	private JLabel lbl_close;
	int posX=0,posY=0;


	/**
	 * Launch the application.
	 */
	private JTextField textField;
	private JTextField textField_1;
	private static Point point = new Point();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndicPassword window = new IndicPassword();
					window.frame.setUndecorated(true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IndicPassword() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		
		lbl_close = new JLabel("X");
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_close.setForeground(Color.RED);
		lbl_close.setBounds(402, 0, 48, 14);
		getContentPane().add(lbl_close);
		
		lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(36, 96, 87, 14);
		getContentPane().add(lblNewLabel);
		
		lblMotDePasse = new JLabel("Mot de passe oubli\u00E9 ?");
		lblMotDePasse.setForeground(Color.LIGHT_GRAY);
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMotDePasse.setBackground(Color.WHITE);
		lblMotDePasse.setBounds(69, 11, 293, 31);
		getContentPane().add(lblMotDePasse);
		
		lblVousInquieter = new JLabel("Vous inqui\u00E9tez pas je vais vous aider !");
		lblVousInquieter.setForeground(Color.GRAY);
		lblVousInquieter.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVousInquieter.setBackground(Color.WHITE);
		lblVousInquieter.setBounds(85, 42, 242, 32);
		getContentPane().add(lblVousInquieter);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String username = textField.getText().toString();
				String sql = "select password from utilisateurs where username = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,username);
					resultat = prepared.executeQuery();
					if(resultat.next()) {
					String pass = resultat.getString("password");
					String pass1 = pass.substring(0,3);
					textField_1.setText("Les 3 premieres lettres sont :"+pass1+"******");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField.setForeground(Color.WHITE);
		textField.setBackground(UIManager.getColor("CheckBox.foreground"));
		textField.setBounds(133, 91, 248, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setForeground(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(36, 146, 345, 31);
		getContentPane().add(textField_1);	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cnx = ConnexionMySql.ConnexionDB();
		frame.addMouseListener(new MouseAdapter() {
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
