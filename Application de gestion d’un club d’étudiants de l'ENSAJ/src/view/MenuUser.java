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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Date 30/05/2019
 * Il s'agit d'un Menu utilisateur la consultation
 * @author Pavilion
 *
 */
public class MenuUser extends JFrame {

	private JPanel contentPane;
	public JLabel lblMembrePersonnel;
	String role = "Secrétaire général";
	String sql1 = "select username,password,Role from utilisateurs" ;
	int posX=0,posY=0;
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
					MenuUser frame = new MenuUser();
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
	public MenuUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		lblMembrePersonnel = new JLabel("Membre Personnel");
		lblMembrePersonnel.setForeground(Color.WHITE);
		lblMembrePersonnel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMembrePersonnel.setBounds(15, 355, 259, 51);
		contentPane.add(lblMembrePersonnel);
		
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
		lblGestionDesUtilisateurs.setBounds(460, 116, 148, 21);
		contentPane.add(lblGestionDesUtilisateurs);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultationEtudiants obj = new ConsultationEtudiants();
				obj.setPreferredSize(new Dimension(829, 417));
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_1.setIcon(new ImageIcon(MenuUser.class.getResource("/img/Getudiants.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(327, 43, 91, 76);
		contentPane.add(button_1);
		
		JLabel lblGestionDesEtudiants = new JLabel("Gestion des etudiants");
		lblGestionDesEtudiants.setBounds(313, 116, 141, 21);
		contentPane.add(lblGestionDesEtudiants);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultationAbsence obj = new ConsultationAbsence();
				obj.setPreferredSize(new Dimension(755, 420));
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
		button_2.setBounds(476, 43, 91, 76);
		contentPane.add(button_2);
		
		JLabel lblGestionDesvnements = new JLabel("Gestion des \u00E9v\u00E9nements");
		lblGestionDesvnements.setBounds(313, 237, 148, 21);
		contentPane.add(lblGestionDesvnements);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultationEvenement obj = new consultationEvenement();
				obj.setPreferredSize(new Dimension(835, 420));
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
		button_3.setBounds(327, 164, 91, 76);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultationPv obj = new ConsultationPv();
				obj.setPreferredSize(new Dimension(725, 420));;
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
		button_4.setBounds(476, 164, 91, 76);
		contentPane.add(button_4);
		
		JLabel lblGestionDesPvs = new JLabel("Gestion des PVS");
		lblGestionDesPvs.setBounds(476, 235, 103, 21);
		contentPane.add(lblGestionDesPvs);
		
		JLabel label = new JLabel("Gestion des comptabilit\u00E9s");
		label.setBounds(306, 360, 148, 21);
		contentPane.add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultationComptabilite obj = new ConsultationComptabilite();
				obj.setPreferredSize(new Dimension(750, 420));;
				obj.setUndecorated(true);	    
				obj.pack();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			
			}
		});
		button.setIcon(new ImageIcon(MenuUser.class.getResource("/img/Comptabilite.png")));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBackground(Color.WHITE);
		button.setBounds(327, 285, 91, 76);
		contentPane.add(button);
		
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
