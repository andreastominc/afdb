package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import bl.AfdbHinzufuegen;
import bl.AfdbJTableModel;
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
import java.awt.GridBagLayout;
import javax.swing.JSeparator;

public class AfdbZugewiesenFrame extends JFrame {

	private JPanel contentPane;
	private Benutzer eingeloggterUser;
	private JLabel lblEingeloggterUser;
	
	private static AfdbZugewiesenFrame frame; // als private static definieren, damit spaeter "frame.dispose" aufgerufen werden kann.
	private JTable tblAnforderungen;
	private JScrollPane scrollPane;
	private AfdbJTableModel datamodel;
	
	/**
	 * diese Methode muss aufgerufen werden, damit die Tabelle mit den Anforderungen 
	 * des aktuell eingeloggten Benutzers befuellt und angezeigt wird.
	 */
	protected void initializeData() {
		initializeTable();
		fillTable();
	}

	/**
	 * Create the frame.
	 */
	public AfdbZugewiesenFrame() {
		setTitle("Mir zugewiesene Anforderungen");
		frame = this;
		frame.setLocationRelativeTo(null);
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
				/* // macht keinen Sinn das gleiche Fenster zu schliessen und nochmal zu oeffnen... 
				frame.dispose(); // aktuelles Frame schliessen
				AfdbZugewiesenFrame zugew_frame = new AfdbZugewiesenFrame();
				zugew_frame.setBounds(300, 100, 1000, 600);
				zugew_frame.setMinimumSize(new Dimension(1100, 700));
				zugew_frame.setVisible(true); // das "Mir zugewiesen"-Frame oeffnen und anzeigen
				*/
			}
		});
		menuBar.add(mntmMirZugewiesen);
		
		// bei Klick aufs Menue-Item "Hinzufuegen" soll das aktuelle Frame "geschlossen" werden und das 
		// neue Frame "Hinzufuegen" geoeffnet werden.
		JMenuItem mntmHinzufuegen = new JMenuItem("Hinzufuegen");
		mntmHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hinzufuegen");
				frame.dispose(); // aktuelles Frame schliessen
				AfdbHinzufuegenFrame hinzu_frame = new AfdbHinzufuegenFrame();
				hinzu_frame.setEingeloggterUser(eingeloggterUser);
				hinzu_frame.setBounds(300, 100, 1000, 600);
				hinzu_frame.setMinimumSize(new Dimension(1100, 700));
				hinzu_frame.setVisible(true); // das Hinzufuegen-Frame oeffnen und anzeigen
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		menuBar.add(mntmHinzufuegen);
		
		// bei Klick aufs Menue-Item "Suchen" soll das aktuelle Frame "geschlossen" werden und das 
		// neue Frame "Suchen" geoeffnet werden.
		JMenuItem mntmSuchen = new JMenuItem("Suchen");
		mntmSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Suchen"); // auf Konsole mitloggen dass "Suchen" angeklickt wurde
				frame.dispose(); // aktuelles Frame schliessen
				AfdbSuchenFrame suche_frame = new AfdbSuchenFrame();
				suche_frame.setEingeloggterUser(eingeloggterUser);
				suche_frame.setBounds(300, 100, 1000, 600);
				suche_frame.setMinimumSize(new Dimension(1100, 700));
				suche_frame.initializeData();
				suche_frame.setVisible(true); // das Suchen-Frame oeffnen und anzeigen
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator_1);
		menuBar.add(mntmSuchen);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator_2);
		
		JLabel lblAngemeldetAls = new JLabel("Angemeldet als: ");
		menuBar.add(lblAngemeldetAls);
		
		lblEingeloggterUser = new JLabel("");
		menuBar.add(lblEingeloggterUser);
		//----------------------------------------------------------------

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane((Component) null);
		panel_1.add(scrollPane); 
		
	}
	
	/**
	 * Befuellen der Tabelle mit jenen Anforderungen,
	 * zu denen der aktuell eingeloggte Benutzer zugewiesen ist.
	 */
	private void fillTable(){
		AfdbSuchen blSuchen = new AfdbSuchen();
		
		List<Anforderung> anforderungen = blSuchen.suchen(0,"","","",eingeloggterUser.getBenutzername(),"","");
		if(anforderungen.isEmpty())
		{
			// Tabelle leeren
			while (datamodel.getRowCount() > 0) {
				datamodel.removeRow(0);
			}
			//JOptionPane.showMessageDialog(this,"Keine Daten gefunden!");
		}
		else
		{
			//JOptionPane.showMessageDialog(this,"Suche erfolgreich!");
			this.anforderungenAnzeigen(anforderungen);
		}
	}
	

 	private void initializeTable(){
 		//String[] columnNames = {"AnfID", "Priorität", "Status", "Titel", "Kunde", "Gepl. Fertigstellung", "Helpdesknr."};
 		datamodel = new AfdbJTableModel();

		this.tblAnforderungen = new JTable();
		this.tblAnforderungen.setModel(datamodel);
		this.tblAnforderungen.setAutoCreateRowSorter(true);
		this.scrollPane.setViewportView(tblAnforderungen);
		
		tblAnforderungen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
				// Bei Doppelklick einer Zeile soll etwas gemacht werden..
				tblAnforderungen.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		            	if (e.getClickCount() == 2 && !e.isConsumed()) {
		            	     e.consume();
		            	     //handle double click event
		            	     
		            	     System.out.println("row="+tblAnforderungen.getSelectedRow());
		                    System.out.println("rowdata="+datamodel.getSelectedRow(tblAnforderungen.getSelectedRow()).toString());
		                     
		                   //  neuer Frame zum Bearbeiten der angeklickten Anf. soll geoeffnet werden... Suchen-Frame wird geschlossen.
		                   frame.dispose(); // aktuelles Frame schliessen
		     				AfdbBearbeitenFrame bearb_frame = new AfdbBearbeitenFrame();
		     				bearb_frame.setEingeloggterUser(eingeloggterUser);
		     				// die via Doppelklick in der Tabell ausgewaehlte Anforderung an das Bearbeiten-Frame uebergeben:
		     				bearb_frame.setAnf(datamodel.getSelectedRow(tblAnforderungen.getSelectedRow()));
		     				
		     				bearb_frame.setBounds(300, 100, 1000, 600);
		     				bearb_frame.setMinimumSize(new Dimension(1100, 700));
		     				bearb_frame.initializeData();
		     				//bearb_frame.setUsername("Testuser123"); // hier dann den Usernamen des eingeloggten Users uebergeben.
		     				bearb_frame.setVisible(true); // das Bearbeiten-Frame oeffnen und anzeigen
		            	
		            	}
		            }
				});	
 		
 	}	
 	
 	/**
 	 * Anzeigen der Anforderungen, die in der Liste vorhanden sind
 	 * @param anforderungen
 	 */
	private void anforderungenAnzeigen(List<Anforderung> anforderungen)
	{
		// vor jedem Aufruf der Methode die Tabelle leeren
		while (datamodel.getRowCount() > 0) {
			datamodel.removeRow(0);
		}
		
		// Die Tabelle mit den Daten der gefundenen Anforderungen befuelllen
		for (Anforderung anf : anforderungen)
		{
			// Reihenfolge der Tabellen-Ueberschriften: {"AnfID", "Priorität", "Status", "Titel", "Kunde", "Gepl. Fertigstellung", "Helpdesknr."};
			//datamodel.addRow(new Object[]{anf.getAnfId()+"",anf.getPrio().getBezeichnung(),anf.getStatus().getBezeichnung(),anf.getTitel(),anf.getKunde().getBezeichnung(),anf.getFertiggeplant(),anf.getHdNummer()});
			datamodel.addRow(anf);
		}
	}

	public Benutzer getEingeloggterUser() {
		return eingeloggterUser;
	}

	public void setEingeloggterUser(Benutzer eingeloggterUser) {
		this.eingeloggterUser = eingeloggterUser;
		this.lblEingeloggterUser.setText(eingeloggterUser.getBenutzername());
	}
	
	
	
}