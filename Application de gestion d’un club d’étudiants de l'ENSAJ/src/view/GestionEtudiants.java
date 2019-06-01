package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ConnexionMySql;
import net.proteanit.sql.DbUtils;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

/**
 * Date 30/05/2019
 * Il s'agit d'une interface graphique des gestions des etudiants
 * @author Pavilion
 *
 */
public class GestionEtudiants extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox ;
	int posX=0,posY=0;
	Connection cnx = null ;
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	String id = null; 
	/**
	 * quitterl'application
	 */
	void fermer() {
		dispose();
	}

	/**
	 * Launch the application.
	 */
	private static Point point = new Point();
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JDateChooser dateChooser ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEtudiants frame = new GestionEtudiants();
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
	public GestionEtudiants() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnexionDB();
		JLabel lblGestionDunClub = new JLabel("Gestion d\u2019un club ");
		lblGestionDunClub.setForeground(Color.WHITE);
		lblGestionDunClub.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDunClub.setBounds(29, 325, 238, 51);
		getContentPane().add(lblGestionDunClub);
		
		JLabel lblDtudiantsDe = new JLabel("d\u2019\u00E9tudiants de");
		lblDtudiantsDe.setForeground(Color.WHITE);
		lblDtudiantsDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDtudiantsDe.setBounds(50, 372, 193, 51);
		getContentPane().add(lblDtudiantsDe);
		
		JLabel lblLensaj = new JLabel("L'ENSAJ");
		lblLensaj.setForeground(Color.WHITE);
		lblLensaj.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLensaj.setBounds(85, 421, 119, 51);
		getContentPane().add(lblLensaj);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(GestionEtudiants.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 256);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GestionEtudiants.class.getResource("/img/archi_urba_deco_background.jpg")));
		lblNewLabel.setBounds(0, 0, 283, 536);
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
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(Color.BLACK);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNom.setBounds(305, 79, 93, 14);
		contentPane.add(lblNom);
		
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
		textField.setBounds(305, 99, 216, 27);
		contentPane.add(textField);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(/**
		 * @author Pavilion
		 * ajouter des etudiants
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = comboBox.getSelectedItem().toString();
				String nom = textField.getText().toString();
				String prenom = textField_1.getText().toString();
				String cin = textField_2.getText().toString();
				String num = textField_3.getText().toString();
				String adresse = textField_4.getText().toString();
				String datedenaissance = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String sql="insert into etudiants(prenom,nom,cin,tel,datedenaissance,adresse,username) values (?,?,?,?,?,?,?)";
						
				try {
					if(!nom.equals("") || !prenom.equals("") || !cin.equals("") || !num.equals("") || !adresse.equals("") || !datedenaissance.equals("")|| !username.equals("Selectionnez")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1,nom);
						prepared.setString(2,prenom);
						prepared.setString(3,cin);
						prepared.setString(4,num);
						prepared.setString(6,adresse);
						prepared.setString(5,datedenaissance);
						prepared.setString(7,username);
						prepared.execute();
						UpdateTabe();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						comboBox.setSelectedItem("Selectionnez");
						JOptionPane.showMessageDialog(null,"L'etudiant est ajoutee ! ");
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
		btnAjouter.setBounds(305, 404, 216, 34);
		contentPane.add(btnAjouter);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.addActionListener(/**
		 * @author Pavilion
		 * supprimer des etudiants
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				id = table.getModel().getValueAt(ligne,0).toString();
				String sql = "delete from etudiants where id_etudiant ='"+id+"'" ;
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.execute();
					JOptionPane.showMessageDialog(null,"Etudiant Supprime ! ");
					UpdateTabe();
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					comboBox.setSelectedItem("Selectionnez");
					//UpdateTabe();					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBackground(Color.RED);
		button_1.setBounds(305, 480, 216, 34);
		contentPane.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(543, 94, 326, 393);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*int ligne = table.getSelectedRow();
				id = table.getModel().getValueAt(ligne,0).toString();
				String sql = "select * from etudiants where id_etudiant='"+id+"' ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.executeQuery();
					
					if(resultat.next()) {
						textField.setText(resultat.getString("nom"));
						textField_1.setText(resultat.getString("prenom"));
						textField_2.setText(resultat.getString("cin"));
						textField_3.setText(resultat.getString("num"));
						textField_4.setText(resultat.getString("adresse"));
						textField_5 .setText(resultat.getString("datedenaissance"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

				int ligne = table.getSelectedRow();
				String prenom = table.getModel().getValueAt(ligne,1).toString();
				String nom = table.getModel().getValueAt(ligne,2).toString();
				String cin = table.getModel().getValueAt(ligne,3).toString();
				String num = table.getModel().getValueAt(ligne,4).toString();
				String adresse = table.getModel().getValueAt(ligne,6).toString();
				String datedenaissance = table.getModel().getValueAt(ligne,5).toString();
				String username = table.getModel().getValueAt(ligne,7).toString();
				textField.setText(nom);
				textField_1.setText(prenom);
				textField_2.setText(cin);
				textField_3.setText(num);
				textField_4.setText(adresse);
				comboBox.setSelectedItem(username);
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
		btnActualiser.setBounds(543, 49, 129, 34);
		contentPane.add(btnActualiser);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(/**
		 * @author Pavilion
		 * ajouter des etudiants
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				id = table.getModel().getValueAt(ligne,0).toString();
				String sql = "update etudiants set prenom = ? , nom = ? , cin = ? , tel = ? ,datedenaissance = ? ,adresse=?,username=? where id_etudiant = '"+id+"' " ;
				try {
					String date_reunion = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField_1.getText().toString());
					prepared.setString(2,textField.getText().toString());
					prepared.setString(3,textField_2.getText().toString());
					prepared.setString(4,textField_3.getText().toString());
					prepared.setString(6,textField_4.getText().toString());
					prepared.setString(5,date_reunion);
					prepared.setString(7,comboBox.getSelectedItem().toString());
					prepared.execute();
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					comboBox.setSelectedItem("Selectionnez");
					UpdateTabe();
					JOptionPane.showMessageDialog(null,"Utilisateur Modifiee ! ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModifier.setBackground(Color.RED);
		btnModifier.setBounds(305, 442, 216, 34);
		contentPane.add(btnModifier);
		
		JLabel label_2 = new JLabel("<-");
		label_2.addMouseListener(new MouseAdapter() {
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
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(305, 151, 216, 27);
		contentPane.add(textField_1);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(Color.BLACK);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrenom.setBounds(305, 132, 93, 14);
		contentPane.add(lblPrenom);
		
		JLabel lblCin = new JLabel("CIN");
		lblCin.setForeground(Color.BLACK);
		lblCin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCin.setBounds(305, 180, 93, 14);
		contentPane.add(lblCin);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.BLACK);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(305, 203, 216, 27);
		contentPane.add(textField_2);
		
		JLabel lblNumTel = new JLabel("Num Tel ");
		lblNumTel.setForeground(Color.BLACK);
		lblNumTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumTel.setBounds(305, 233, 93, 14);
		contentPane.add(lblNumTel);
		
		textField_3 = new JTextField();
		textField_3.setForeground(Color.BLACK);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(305, 255, 216, 27);
		contentPane.add(textField_3);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setForeground(Color.BLACK);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdresse.setBounds(305, 284, 93, 14);
		contentPane.add(lblAdresse);
		
		textField_4 = new JTextField();
		textField_4.setForeground(Color.BLACK);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(305, 302, 216, 27);
		contentPane.add(textField_4);
		
		JLabel lblDateDeNaissance = new JLabel("Date de Naissance");
		lblDateDeNaissance.setForeground(Color.BLACK);
		lblDateDeNaissance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateDeNaissance.setBounds(305, 336, 119, 14);
		contentPane.add(lblDateDeNaissance);
		
		JButton btnTelecharger = new JButton("Telecharger");
		btnTelecharger.addActionListener(/**
		 * @author Pavilion
		 * Telecharger la liste des etudiants dans une format PDF,Onetouche etc ...
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header =new MessageFormat("Gestion Etudiant");
		        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
		        try {
		            table.print(JTable.PrintMode.NORMAL,header ,footer);
		        } catch (java.awt.print.PrinterException e) {
		            System.err.format("cannot print %s%n",e.getMessage());}
			}
		});
		btnTelecharger.setForeground(Color.WHITE);
		btnTelecharger.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTelecharger.setBackground(Color.RED);
		btnTelecharger.setBounds(686, 49, 129, 34);
		contentPane.add(btnTelecharger);

		
		JLabel label = new JLabel("Nom");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(305, 21, 93, 14);
		contentPane.add(label);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionnez"}));
		comboBox.setToolTipText("");
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(SystemColor.scrollbar);
		comboBox.setBounds(305, 43, 216, 27);
		contentPane.add(comboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(305, 361, 216, 27);
		contentPane.add(dateChooser);
		remplivCombobox();
		
		addMouseListener(new MouseAdapter() {
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
	 * table des etudiants
	 */
	public void UpdateTabe() {
		String sql = "select * from etudiants " ;
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void remplivCombobox()
	{	
		String sql ="select * from utilisateurs" ;
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()) {
				String username = resultat.getString("username").toString();
				comboBox.addItem(username);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
}
