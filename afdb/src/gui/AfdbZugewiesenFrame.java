package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import bl.AfdbHinzufuegen;
import bl.AfdbSuchen;
import bl.BenutzerInfo;
import data.Anforderung;
import data.AnforderungsArt;
import data.Anhang;
import data.Benutzer;
import data.Kunde;
import data.Modul;
import data.Prioritaet;
import data.Status;
import data.Version;
import javax.swing.JTable;

public class AfdbZugewiesenFrame extends JFrame {

	private JPanel contentPane;
	
	private static AfdbZugewiesenFrame frame; // als private static definieren, damit spaeter "frame.dispose" aufgerufen werden kann.
	private JTable tblAnforderungen;
	private DefaultTableModel model;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AfdbZugewiesenFrame(); 
					frame.setVisible(true);
					frame.setBounds(300, 100, 1000, 600);
					frame.setMinimumSize(new Dimension(1100, 700));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


		});
	}
	
	private void initializeData() {
		BenutzerInfo.BenutzerName = "atominc"; // Benutzername aus LogIn
		fillTable();
	}

	/**
	 * Create the frame.
	 */
	public AfdbZugewiesenFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//----------------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenuItem mntmMirZugewiesen = new JMenuItem("Mir zugewiesen");
		mntmMirZugewiesen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mir zugewiesen");
				// to do ....
			}
		});
		menuBar.add(mntmMirZugewiesen);
		
		JMenuItem mntmBearbeiten = new JMenuItem("Hinzufügen");
		mntmBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hinzufügen");
				
				frame.dispose(); // aktuelles Frame schliessen
				AfdbHinzufuegenFrame hinzu_frame = new AfdbHinzufuegenFrame();
				hinzu_frame.setBounds(300, 100, 1000, 600);
				hinzu_frame.setMinimumSize(new Dimension(1100, 700));
				hinzu_frame.setVisible(true); // das Suchen-Frame oeffnen und anzeigen
			}
		});
		menuBar.add(mntmBearbeiten);
		
		// bei Klick aufs Menue-Item "Suchen" soll das aktuelle Frame "geschlossen" werden und das 
		// neue Frame "Suchen" geoeffnet werden.
		JMenuItem mntmSuchen = new JMenuItem("Suchen");
		mntmSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Suchen"); // auf Konsole mitloggen dass "Suchen" angeklickt wurde
				
				frame.dispose(); // aktuelles Frame schliessen
				AfdbSuchenFrame suche_frame = new AfdbSuchenFrame();
				suche_frame.setBounds(300, 100, 1000, 600);
				suche_frame.setMinimumSize(new Dimension(1100, 700));
				suche_frame.initializeData();
				suche_frame.setUsername(BenutzerInfo.BenutzerName);
				suche_frame.setVisible(true); // das Suchen-Frame oeffnen und anzeigen
			}
		});
		menuBar.add(mntmSuchen);
		//----------------------------------------------------------------

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		Object[] columnNames = 
			{"AfNr",
             "Priorität",
             "Status",
             "Titel",
             "Kunde",
             "gepl. Fertigstellung",
             "Helpdesknummer"};
		
		tblAnforderungen = new JTable(new DefaultTableModel(columnNames,0));
		panel_1.add(tblAnforderungen);
		
		panel_1.setLayout(new BorderLayout());
		panel_1.add(tblAnforderungen.getTableHeader(), BorderLayout.PAGE_START);
		panel_1.add(tblAnforderungen, BorderLayout.CENTER);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		initializeData();
	}
	
	private void fillTable (){
		model = (DefaultTableModel) tblAnforderungen.getModel();
		AfdbSuchen blSuchen = new AfdbSuchen();
		List<Anforderung> anfList = blSuchen.suchen(0, "", "", "", "", "", "");//BenutzerInfo.BenutzerName
		for(int i = 0; i < anfList.size(); i++){
			Anforderung anf = anfList.get(i);
			
			model.addRow(new Object[]{
				 	String.valueOf(anf.getAnfId()), 
				 	anf.getPrio().toString(), 
					anf.getStatus().toString(), 
					anf.getTitel(), 
					anf.getKunde().getBezeichnung(), 
					anf.getFertiggeplant(),
					anf.getHdNummer()});	
		}
	}
}