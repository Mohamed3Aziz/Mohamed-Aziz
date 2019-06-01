package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Date 30/05/2019
 * Il s'agit d'un Menu Administrateurs qui sert faire des getions des utilisateurs,des etudiants,des Pvs,des evenements,et d'absences
 * @author Pavilion
 *
 */
public class MenuAdministrateur extends JFrame {

	private JPanel contentPane;
	public JLabel lblGestionDunClub ;
	String role = "Secrétaire général";
	String sql1 = "select username,password,Role from utilisateurs" ;
	int posX=0,posY=0;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrateur frame = new MenuAdministrateur();
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
	public MenuAdministrateur() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblGestionDunClub = new JLabel("Gestion d\u2019un club ");
		lblGestionDunClub.setForeground(Color.WHITE);
		lblGestionDunClub.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDunClub.setBounds(31, 224, 238, 51);
		getContentPane().add(lblGestionDunClub);
		
		JLabel lblDtudiantsDe = new JLabel("d\u2019\u00E9tudiants de");
		lblDtudiantsDe.setForeground(Color.WHITE);
		lblDtudiantsDe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDtudiantsDe.setBounds(56, 264, 193, 51);
		getContentPane().add(lblDtudiantsDe);
		
		JLabel lblAdministrateur = new JLabel("Administrateur");
		lblAdministrateur.setForeground(Color.WHITE);
		lblAdministrateur.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAdministrateur.setBounds(47, 354, 222, 51);
		contentPane.add(lblAdministrateur);
		
		JLabel lblLensaj = new JLabel("L'ENSAJ");
		lblLensaj.setForeground(Color.WHITE);
		lblLensaj.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLensaj.setBounds(84, 308, 119, 51);
		getContentPane().add(lblLensaj);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 215);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/archi_urba_deco_background.jpg")));
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
		lblX.setBounds(598, 0, 44, 14);
		contentPane.add(lblX);
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion des abscences");
		lblGestionDesUtilisateurs.setBounds(302, 236, 148, 21);
		contentPane.add(lblGestionDesUtilisateurs);
		
		JButton button = new JButton("");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.WHITE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUsers obj = new GestionUsers();
				obj.setPreferredSize(new Dimension(895, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/adduser.png")));
		button.setBounds(327, 41, 91, 76);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEtudiants obj = new GestionEtudiants();
				obj.setPreferredSize(new Dimension(897, 537));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_1.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/Getudiants.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(476, 41, 91, 76);
		contentPane.add(button_1);
		
		JLabel lblGestionDesEtudiants = new JLabel("Gestion des etudiants");
		lblGestionDesEtudiants.setBounds(458, 117, 141, 21);
		contentPane.add(lblGestionDesEtudiants);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsence obj = new GestionAbsence();
				obj.setPreferredSize(new Dimension(895, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_2.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/absence.png")));
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(327, 161, 91, 76);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("Gestion des users");
		label.setBounds(310, 117, 119, 21);
		contentPane.add(label);
		
		JLabel lblGestionDesvnements = new JLabel("Gestion des \u00E9v\u00E9nements");
		lblGestionDesvnements.setBounds(460, 236, 148, 21);
		contentPane.add(lblGestionDesvnements);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEvenement obj = new GestionEvenement();
				obj.setPreferredSize(new Dimension(895, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_3.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/event.png")));
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(476, 161, 91, 76);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPV obj = new GestionPV();
				obj.setPreferredSize(new Dimension(895, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_4.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/reunion.png")));
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(327, 279, 91, 76);
		contentPane.add(button_4);
		
		JLabel lblGestionDesPvs = new JLabel("Gestion des PVS");
		lblGestionDesPvs.setBounds(327, 354, 108, 21);
		contentPane.add(lblGestionDesPvs);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionComptabilite obj = new GestionComptabilite();
				obj.setPreferredSize(new Dimension(895, 420));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_5.setIcon(new ImageIcon(MenuAdministrateur.class.getResource("/img/Comptabilite.png")));
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(476, 279, 91, 76);
		contentPane.add(button_5);
		
		JLabel lblGestionDesComptabilit = new JLabel("Gestion des comptabilit\u00E9s");
		lblGestionDesComptabilit.setBounds(451, 354, 148, 21);
		contentPane.add(lblGestionDesComptabilit);
		
		JLabel label_1 = new JLabel("L'ENSAJ");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label_1.setBounds(0, 0, 119, 51);
		contentPane.add(label_1);
		
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
}
