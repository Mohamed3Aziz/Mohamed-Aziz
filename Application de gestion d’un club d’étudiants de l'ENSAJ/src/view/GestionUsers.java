package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ConnexionMySql;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;

/**
 * Date 30/05/2019
 * Il s'agit d'une interface graphique de gestion des utilisateurs
 * @author Pavilion
 *
 */
public class GestionUsers extends JFrame {

	private JPanel contentPane;
	int posX=0,posY=0;
	Connection cnx = null ;
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	String users = null; 
	/**
	 * quitter l'application
	 */
	void fermer() {
		dispose();
	}

	/**
	 * Launch the application.
	 */
	private static Point point = new Point();
	private JTextField textField;
	private JPasswordField passwordField;
	private JTable table;
	private JComboBox comboBox ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsers frame = new GestionUsers();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionUsers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnexionDB();
		
		JLabel lblGestionDunClub = new JLabel("Gestion d\u2019un club ");
		lblGestionDunClub.setForeground(Color.WHITE);
		lblGestionDunClub.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDunClub.setBounds(31, 224, 238, 51);
		getContentPane().add(lblGestionDunClub);
		
		JLabel lblDtudiantsDe = new JLabel("d\u2019\u00E9tudiants de");
		lblDtudiantsDe.setForeground(Color.WHITE);
		lblDtudiantsDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDtudiantsDe.setBounds(56, 264, 193, 51);
		getContentPane().add(lblDtudiantsDe);
		
		JLabel lblLensaj = new JLabel("L'ENSAJ");
		lblLensaj.setForeground(Color.WHITE);
		lblLensaj.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLensaj.setBounds(84, 308, 119, 51);
		getContentPane().add(lblLensaj);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(GestionUsers.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 215);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GestionUsers.class.getResource("/img/archi_urba_deco_background.jpg")));
		lblNewLabel.setBounds(0, 0, 283, 461);
		getContentPane().add(lblNewLabel);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();	
			}
		});
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX.setForeground(Color.RED);
		lblX.setBounds(870, 0, 44, 14);
		contentPane.add(lblX);
		
		JLabel label = new JLabel("Username  ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(305, 33, 93, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				/*String username = textField.getText().toString();
				String sql = "select password from utilisateurs where username = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,username);
					resultat = prepared.executeQuery();
					if(resultat.next()) {
					String pass = resultat.getString("password");
					passwordField.setText(pass);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		textField.setForeground(Color.BLACK);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(305, 51, 216, 26);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Password ");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(305, 88, 93, 14);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(305, 106, 216, 26);
		contentPane.add(passwordField);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(/**
		 * @author Pavilion
		 * ajouter des utilisateurs
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = comboBox.getSelectedItem().toString();
				String sql = "insert into utilisateurs (username,password,Role) values (?,?,?)" ;
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,passwordField.getText().toString());
					prepared.setString(3,role);
					if(!textField.getText().equals("") && !passwordField.getText().equals("") || comboBox.getSelectedItem().equals("Sélectionnez")) {
					prepared.execute();
					UpdateTabe();
					JOptionPane.showMessageDialog(null,"Utilisateur Ajoutee ! ");
					comboBox.setSelectedItem("Sélectionnez");
					textField.setText("");
					passwordField.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null,"Rempliser les champs vides ! ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAjouter.setBackground(Color.RED);
		btnAjouter.setBounds(305, 224, 216, 34);
		contentPane.add(btnAjouter);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.addActionListener(/**
		 * @author Pavilion
		 * supprimer des utilisateurs
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "delete from utilisateurs where username = ? and password = ? and Role = ?" ;
				try {
					if(!textField.getText().equals("") && !passwordField.getText().equals("") ) {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,passwordField.getText().toString());
					prepared.setString(3,comboBox.getSelectedItem().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null,"Utilisateur Supprime ! ");
					comboBox.setSelectedItem("Sélectionnez");
					textField.setText("");
					passwordField.setText("");
					UpdateTabe();}
					else {
					JOptionPane.showMessageDialog(null,"Veuillez selectionnez pour la suppression ! ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBackground(Color.RED);
		button_1.setBounds(305, 312, 216, 34);
		contentPane.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 94, 312, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String NomETUDIANT = table.getModel().getValueAt(ligne,0).toString();
				String password = table.getModel().getValueAt(ligne,1).toString();
				String role = table.getModel().getValueAt(ligne,2).toString();
				textField.setText(NomETUDIANT);
				passwordField.setText(password);
				comboBox.setSelectedItem(role);
				
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateTabe();
			}
		});
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualiser.setBackground(Color.RED);
		btnActualiser.setBounds(557, 50, 129, 34);
		contentPane.add(btnActualiser);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne,0).toString();
				String sql = "update utilisateurs set username = ? , password = ? , Role = ? where username = '"+id+"' " ;
				try {
					if(!textField.getText().equals("") && !passwordField.getText().equals("") ) {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,passwordField.getText().toString());
					prepared.setString(3,comboBox.getSelectedItem().toString());
					prepared.execute();
					UpdateTabe();
					comboBox.setSelectedItem("Sélectionnez");
					textField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null,"Utilisateur Modifiee ! ");}
					else {
				    JOptionPane.showMessageDialog(null,"Veuilez selectionnez pour la modification ! ");}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModifier.setBackground(Color.RED);
		btnModifier.setBounds(305, 267, 216, 34);
		contentPane.add(btnModifier);
		
		JLabel label_2 = new JLabel("<-");
		label_2.addMouseListener(/**
		 * @author Pavilion
		 * retourner au menu
		 */
		new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MenuAdministrateur obj = new MenuAdministrateur();
				obj.setPreferredSize(new Dimension(625, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(835, 0, 44, 14);
		contentPane.add(label_2);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(Color.BLACK);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRole.setBounds(305, 143, 93, 14);
		contentPane.add(lblRole);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "Administrateur", "Tr\u00E9sorier", "Secr\u00E9taire g\u00E9n\u00E9ral", "Membre Personnel"}));
		comboBox.setToolTipText("");
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(SystemColor.scrollbar);
		comboBox.setBounds(305, 161, 216, 27);
		contentPane.add(comboBox);
		
		addMouseListener(/**
		 * @author Pavilion
		 * deplacer l'application
		 */
		new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		        point.x = e.getX();
		        point.y = e.getY();
		      }
		    });
		    addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		        Point p = getLocation();
		        setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
		      }
		    });
	}
	/**
	 * table des utilisateurs
	 */
	public void UpdateTabe() {
		String sql = "select username,password,Role from utilisateurs " ;
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
