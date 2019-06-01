package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;

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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

/**
 * Date 30/05/2019
 * Il s'agit d'une interface graphique de consultation des etudiants
 * @author Pavilion
 * 
 */
public class ConsultationEtudiantsTresorie extends JFrame {

	private JPanel contentPane;
	int posX=0,posY=0;
	Connection cnx = null ;
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	String id = null;
	String users = null;
	/**
	 * Fermer l'application en cliquant sur X
	 */
	void fermer() {
		dispose();
	}
	/**
	 * Launch the application.
	 */
	private static Point point = new Point();
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultationEtudiantsTresorie frame = new ConsultationEtudiantsTresorie();
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
	public ConsultationEtudiantsTresorie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 417);
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
		lblNewLabel_2.setIcon(new ImageIcon(GestionPV.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 215);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GestionPV.class.getResource("/img/archi_urba_deco_background.jpg")));
		lblNewLabel.setBounds(0, 0, 283, 461);
		getContentPane().add(lblNewLabel);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(/**
		 * @author Pavilion
		 * quitter l'application
		 */
		new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();	
			}
		});
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX.setForeground(Color.RED);
		lblX.setBounds(810, 0, 44, 14);
		contentPane.add(lblX);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 99, 495, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(/**
		 * @author Pavilion
		 * Actualiser la table 
		 */
		new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				UpdateTabe();
			}
		});
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualiser.setBackground(Color.RED);
		btnActualiser.setBounds(311, 49, 129, 34);
		contentPane.add(btnActualiser);
		
		JLabel label_2 = new JLabel("<-");
		label_2.addMouseListener(/**
		 * @author Pavilion
		 * Retourner au menu
		 */
		new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MenuTresorie obj = new MenuTresorie();
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
		label_2.setBounds(783, 0, 44, 14);
		contentPane.add(label_2);
		
		JButton btnTelecharger = new JButton("Telecharger");
		btnTelecharger.addActionListener(/**
		 * @author Pavilion
		 * Telecharger la liste des etudiants dans une format PDF,Onetouche etc ...
		 */
		new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header =new MessageFormat("Gestion Etudiant	");
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
		btnTelecharger.setBounds(462, 49, 129, 34);
		contentPane.add(btnTelecharger);
		
		addMouseListener(/**
		 * @author Pavilion
		 * Deplacer l'application
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
	 * Table des utilisateurs
	 */
	public void UpdateTabe() {
		String sql = "select prenom,nom,Role,cin,tel,datedenaissance,adresse from etudiants,utilisateurs where utilisateurs.username = etudiants.username order by utilisateurs.role Asc  " ;
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


