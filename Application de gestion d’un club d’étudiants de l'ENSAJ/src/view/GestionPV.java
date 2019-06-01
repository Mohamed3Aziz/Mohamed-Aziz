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
 * Il s'agit d'une interface graphique de getion des Pvs
 * @author Pavilion
 *
 */
public class GestionPV extends JFrame {

	private JPanel contentPane;
	int posX=0,posY=0;
	Connection cnx = null ;
	PreparedStatement prepared = null ;
	ResultSet resultat = null ;
	String id = null;
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
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JDateChooser dateChooser ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPV frame = new GestionPV();
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
	public GestionPV() {
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
		lblNewLabel_2.setIcon(new ImageIcon(GestionAbsence.class.getResource("/img/bg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 283, 215);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GestionAbsence.class.getResource("/img/archi_urba_deco_background.jpg")));
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
		lblX.setBounds(870, 0, 44, 14);
		contentPane.add(lblX);
		
		JLabel lblNomEtudiant = new JLabel("Numero Reunion");
		lblNomEtudiant.setForeground(Color.BLACK);
		lblNomEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomEtudiant.setBounds(306, 50, 128, 14);
		contentPane.add(lblNomEtudiant);
		
		JLabel lblDatDeLadsence = new JLabel("date Reunion");
		lblDatDeLadsence.setForeground(Color.BLACK);
		lblDatDeLadsence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatDeLadsence.setBounds(305, 113, 129, 14);
		contentPane.add(lblDatDeLadsence);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(/**
		 * @author Pavilion
		 * ajouter des Pvs
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_reunion = textField.getText().toString();
				String date_reunion = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String description = textField_1.getText().toString();
				String sql = "insert into reunion (numero_reunion,date_reunion,description) values (?,?,?)" ;
				try {
					if (numero_reunion.equals("") || date_reunion.equals("") || description.equals("")) {
						JOptionPane.showMessageDialog(null,"Remplisez les champs !");
					}
					else {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1,numero_reunion);
						prepared.setString(2,date_reunion);
						prepared.setString(3,description);
						prepared.execute();
						UpdateTabe();
						JOptionPane.showMessageDialog(null,"Reunion Ajoutee !");
						textField.setText("");
						textField_1.setText("");
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
		btnAjouter.setBounds(306, 252, 216, 34);
		contentPane.add(btnAjouter);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.addActionListener(/**
		 * @author Pavilion
		 * supprimer les Pvs
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				if( ligne == -1) {
					JOptionPane.showMessageDialog(null,"Selectionnez Reunion! ");
				}
				else {
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from reunion where id_reunion = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.execute();
						JOptionPane.showMessageDialog(null,"Reunion Supprimee ! ");
						UpdateTabe();
						textField.setText("");
						textField_1.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
							}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBackground(Color.RED);
		button_1.setBounds(307, 336, 216, 34);
		contentPane.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 94, 312, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String numero = table.getModel().getValueAt(ligne,1).toString();
				String date = table.getModel().getValueAt(ligne,2).toString();
				String description = table.getModel().getValueAt(ligne,3).toString();
				textField.setText(numero);
				textField_1.setText(description);
				dateChooser.setDateFormatString(date);
				//passwordField.setText(password);
				
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
		btnModifier.addActionListener(/**
		 * @author Pavilion
		 * modifier les pVs
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = table.getSelectedRow();
				String numero_reunion = textField.getText().toString();
				String date_reunion = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String description = textField_1.getText().toString();

				if( ligne == -1) {
					JOptionPane.showMessageDialog(null,"Selectionnez Abscence ! ");
				}
				else {
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "Update reunion set numero_reunion = ? , date_reunion = ? , description = ? where id_reunion = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1,numero_reunion);
						prepared.setString(2,date_reunion);
						prepared.setString(3,description);
						prepared.execute();
						JOptionPane.showMessageDialog(null,"Reunion Modifiee ! ");
						UpdateTabe();
						textField.setText("");
						textField_1.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModifier.setBackground(Color.RED);
		btnModifier.setBounds(306, 295, 216, 34);
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
		
		
		JLabel lblEtatLadsence = new JLabel("description");
		lblEtatLadsence.setForeground(Color.BLACK);
		lblEtatLadsence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEtatLadsence.setBounds(306, 173, 129, 14);
		contentPane.add(lblEtatLadsence);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(306, 135, 216, 27);
		contentPane.add(dateChooser);
		
		JButton btnTelecharger = new JButton("Telecharger");
		btnTelecharger.addActionListener(/**
		 * @author Pavilion
		 * Telecharger la liste des renions dans une format PDF,Onetouche etc ...
		 */
		new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header =new MessageFormat("Gestion PV	");
		        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
		        try {
		            table.print(JTable.PrintMode.NORMAL,header ,footer);
		        } catch (java.awt.print.PrinterException e) {
		            System.err.format("cannot print %s%n",e.getMessage());}
				/*Document Doc = new Document();
				
				try {
					PdfWriter.getInstance(Doc, new FileOutputStream("C:\\Users\\Pavilion\\Desktop\\Document\\Absence.pdf"));
					Doc.open();
					Image img = Image.getInstance("C:\\Users\\Pavilion\\Desktop\\1.png");
					img.scaleAbsoluteWidth(600);
					img.scaleAbsoluteHeight(100);
					img.setAlignment(Image.ALIGN_CENTER);
					Doc.add(img);
					Doc.add(new Paragraph(" "));
					Doc.add(new Paragraph("Gestion D'absence"));
					Doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(3);
					table.setWidthPercentage(100);
					
					PdfPCell cell ;
					cell = new PdfPCell(new Phrase("Nom et Prenom ",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Date D'absence ",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Etat D'absence ",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(EtudcomboBox.getSelectedItem().toString(),FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText(),FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(comboBox.getSelectedItem().toString(),FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					
					table.addCell(cell);
					
					Doc.add(table);
					Doc.close(); 
					Desktop.getDesktop().open(new File ("C:\\Users\\Pavilion\\Desktop\\Document\\Absence.pdf"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		btnTelecharger.setForeground(Color.WHITE);
		btnTelecharger.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTelecharger.setBackground(Color.RED);
		btnTelecharger.setBounds(696, 49, 129, 34);
		contentPane.add(btnTelecharger);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(306, 70, 216, 27);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(306, 193, 216, 27);
		contentPane.add(textField_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(306, 135, 216, 27);
		contentPane.add(dateChooser);
		
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
	 * table des reunions
	 */
	public void UpdateTabe() {
		String sql = "select * from reunion " ;
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


