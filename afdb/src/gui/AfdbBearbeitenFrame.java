package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import dal.QueryHelper;
import data.Anforderung;
import data.AnforderungsArt;
import data.Anhang;
import data.Benutzer;
import data.Kunde;
import data.Modul;
import data.Prioritaet;
import data.Status;
import data.Version;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

import bl.AfdbBearbeiten;
import bl.AfdbHinzufuegen;

public class AfdbBearbeitenFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitel;
	private JTextField tfHelpdesknummer;
	private JTextField tfKopieAn;
	private JTextField tfVerwAnf;
	private JTextField tfSchluesselbegriffe;
	private JTextField tfAnhang;
	private JTextField tfGespAnhaenge;
	private JTextField tfAufwand;
	private JComboBox cbStatus;
	private JComboBox cbModul;
	private JComboBox cbAnforderungsArt;
	private JComboBox cbPrio;
	private JComboBox cbVersion;
	private JComboBox cbZugewiesen;
	private JComboBox cbKunde;
	private JComboBox cbAnsprechperson;
	private JTextArea taBeschreibung;
	private JLabel lblTitel;
	private JLabel lblBeschreibung;
	private JLabel lblHelpdesknummer;
	private JDateChooser dcFertigStellGepl;
	private String filepath = "";
	private File file;
	
	//Object for Business Logic
	private AfdbBearbeiten afdbBl;
	private List<Benutzer> ansprPersListe;
	private List<Benutzer> benutzerListe;
	
	private static AfdbBearbeitenFrame frame; // als private static definieren, damit spaeter "frame.dispose" aufgerufen werden kann.
	private Anforderung anf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setBounds(300, 100, 1000, 600);
					frame.setMinimumSize(new Dimension(1100, 700));
					
					frame.initializeData();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void initializeData() {
		initializeCbStatus(anf);
		initializeCbModul(anf);
		initializeCbPrio(anf);
		initializeCbAnforderungsArt(anf);
		initializeCbVersion(anf);
		initializecbZugewiesen(anf);
		initializecbKunde(anf);
		//initializecbAnsprechperson();
		initializeOthers(anf);
		System.out.println("---bearb-anf="+frame.getAnf().toString());
	}
	
	/**
	 * Die Textfelder, Datumsfelder etc. mit den Daten der aktuell 
	 * zu bearbeitenden Anforderung befuellen.
	 */
	private void initializeOthers(Anforderung a){
		tfTitel.setText(a.getTitel());
		tfHelpdesknummer.setText(a.getHdNummer());
		taBeschreibung.setText(a.getBeschreibung());
		tfAufwand.setText(a.getAufwandGeschaetzt()+"");
		dcFertigStellGepl.setDate(a.getFertiggeplant());
		//tfKopieAn.setText(a.get....);
		tfVerwAnf.setText(a.getVerwAnforderungen());
		tfSchluesselbegriffe.setText(a.getSchluesselBegriffe());
	}

	/**
	 * Create the frame.
	 */
	public AfdbBearbeitenFrame() {
		frame = this;
		this.afdbBl = new AfdbBearbeiten();
		
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
		
		JMenuItem mntmBearbeiten = new JMenuItem("Bearbeiten");
		mntmBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Bearbeiten");
				// to do ....
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
				suche_frame.setVisible(true); // das Suchen-Frame oeffnen und anzeigen
			}
		});
		menuBar.add(mntmSuchen);
		//----------------------------------------------------------------

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);
		panel_9.setLayout(new GridLayout(8, 1, 0, 0));
		
		JLabel lblKunde = new JLabel("Kunde:");
		panel_9.add(lblKunde);
		
		JLabel lblAnsprechperson = new JLabel("Ansprechperson:");
		panel_9.add(lblAnsprechperson);
		
		JLabel lblAnforderungsart = new JLabel("Anforderungsart:");
		panel_9.add(lblAnforderungsart);
		
		JLabel lblPrioritaet = new JLabel("Priorit\u00E4t:");
		panel_9.add(lblPrioritaet);
		
		JLabel lblVerison = new JLabel("Version:");
		panel_9.add(lblVerison);
		
		lblTitel = new JLabel("Titel:");
		panel_9.add(lblTitel);
		
		lblHelpdesknummer = new JLabel("Helpdesknummer:");
		panel_9.add(lblHelpdesknummer);
		
		lblBeschreibung = new JLabel("Beschreibung:");
		panel_9.add(lblBeschreibung);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(8, 1, 0, 0));
		
		cbKunde = new JComboBox();
		cbKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kunde selektierterKd = (Kunde) cbKunde.getSelectedItem();
				//Ansprechpersonen des Kunden setzen
				ansprPersListe = afdbBl.getAnsprechpersonVonKunde(selektierterKd);
				cbAnsprechperson.removeAllItems();
				for(Benutzer ansprPers : ansprPersListe)
				{
					cbAnsprechperson.addItem(ansprPers);
				}
			}
		});
		
		panel_6.add(cbKunde);
		
		cbAnsprechperson = new JComboBox();
		panel_6.add(cbAnsprechperson);
		
		cbAnforderungsArt = new JComboBox();
		panel_6.add(cbAnforderungsArt);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));
		
		cbPrio = new JComboBox();
		panel_7.add(cbPrio);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(JLabel.RIGHT);
		lblStatus.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		panel_7.add(lblStatus);
				
		cbStatus = new JComboBox();
		panel_7.add(cbStatus);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 3, 0, 0));
		
		cbVersion = new JComboBox();
		panel_8.add(cbVersion);
		
		JLabel lblModul = new JLabel("Modul:");
		lblModul.setHorizontalAlignment(JLabel.RIGHT);
		lblModul.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		panel_8.add(lblModul);
		
		cbModul = new JComboBox();
		panel_8.add(cbModul);
		
		tfTitel = new JTextField();
		panel_6.add(tfTitel);
		tfTitel.setColumns(10);
		
		
		tfHelpdesknummer = new JTextField();
		panel_6.add(tfHelpdesknummer);
		tfHelpdesknummer.setColumns(10);
		
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		taBeschreibung = new JTextArea();
		panel_4.add(taBeschreibung, BorderLayout.CENTER);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		taBeschreibung.setBorder(border);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new GridLayout(8, 1, 0, 0));
		
		JLabel lblKopieAn = new JLabel("Kopie an:");
		panel_10.add(lblKopieAn);
		
		JLabel lblAufwand = new JLabel("Aufwand:");
		panel_10.add(lblAufwand);
		
		JLabel lblVerwAnf = new JLabel("verw. Anf.:");
		panel_10.add(lblVerwAnf);
		
		JLabel lblSchluesselbegriffe = new JLabel("Schl\u00FCsselbegriffe");
		panel_10.add(lblSchluesselbegriffe);
		
		JLabel lblZugewiesen = new JLabel("Zugewiesen:");
		panel_10.add(lblZugewiesen);
		
		JLabel lblAnhnge = new JLabel("Anhang:");
		panel_10.add(lblAnhnge);
		
		JLabel lblGespeicherteAnhnge = new JLabel("Gesp. Anh\u00E4nge:");
		panel_10.add(lblGespeicherteAnhnge);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new GridLayout(8, 1, 0, 0));
		
		tfKopieAn = new JTextField();
		panel_11.add(tfKopieAn);
		tfKopieAn.setColumns(10);
		
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 3, 0, 0));
		
		tfAufwand = new JTextField();
		panel_12.add(tfAufwand);
		tfAufwand.setColumns(10);
		
		
		JLabel lblGeplFertigstellung = new JLabel("Gepl. Fertigstellung:");
		lblGeplFertigstellung.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeplFertigstellung.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		panel_12.add(lblGeplFertigstellung);
		
		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));
		
		dcFertigStellGepl = new JDateChooser();
		panel_16.add(dcFertigStellGepl);
		
		tfVerwAnf = new JTextField();
		panel_11.add(tfVerwAnf);
		tfVerwAnf.setColumns(10);
		
		
		tfSchluesselbegriffe = new JTextField();
		panel_11.add(tfSchluesselbegriffe);
		tfSchluesselbegriffe.setColumns(10);
		
		
		cbZugewiesen = new JComboBox();
		panel_11.add(cbZugewiesen);
		
		JPanel panel_15 = new JPanel();
		panel_11.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		tfAnhang = new JTextField();
		panel_15.add(tfAnhang, BorderLayout.CENTER);
		tfAnhang.setColumns(10);
		//to do.. zugewiesene anhaenge anzeigen..
		
		
		JButton btHinzufuegen = new JButton("Hinzuf\u00FCgen");
		btHinzufuegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int returnWert = chooser.showOpenDialog(null);
		        if (returnWert == JFileChooser.APPROVE_OPTION) {
		        	filepath = chooser.getSelectedFile().getPath();
		        	file = chooser.getSelectedFile();
		        	tfAnhang.setText(filepath);
		        }
			}
		});
		panel_15.add(btHinzufuegen, BorderLayout.EAST);
		
		tfGespAnhaenge = new JTextField();
		panel_11.add(tfGespAnhaenge);
		tfGespAnhaenge.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.EAST);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pruefePflichtfelder())
				{
					createAfdb();
				};
			}
		});
		panel_14.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		panel_14.add(btnAbbrechen);
	}
	
	private void initializeCbStatus(Anforderung a)
	{
		List<Status> statusListe = afdbBl.getAllStatus();
		for(Status status : statusListe)
		{
			cbStatus.addItem(status);
		}
		
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbStatus.getModel().setSelectedItem(a.getStatus());
	}
	
	private void initializeCbModul(Anforderung a)
	{
		List<Modul> modulListe = afdbBl.getAllModule();
		for(Modul modul : modulListe)
		{
			cbModul.addItem(modul);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbModul.getModel().setSelectedItem(a.getModul());
	}
	
	private void initializeCbAnforderungsArt(Anforderung a)
	{
		List<AnforderungsArt> anfArtListe =  afdbBl.getAllAnforderungsart();
		for(AnforderungsArt anfArt : anfArtListe)
		{
			cbAnforderungsArt.addItem(anfArt);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbAnforderungsArt.getModel().setSelectedItem(a.getAnfArt());
	}
	
	private void initializeCbPrio(Anforderung a)
	{
		List<Prioritaet> prioListe = afdbBl.getAllPrioritaeten();
		for(Prioritaet prio : prioListe)
		{
			cbPrio.addItem(prio);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbPrio.getModel().setSelectedItem(a.getPrio());
	}
	
	private void initializeCbVersion(Anforderung a)
	{
		List<Version> versionListe = afdbBl.getAllVersionen();
		for(Version version : versionListe)
		{
			cbVersion.addItem(version);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbVersion.getModel().setSelectedItem(a.getVersion());
	}

	private void initializecbZugewiesen(Anforderung a)
	{
		boolean schreibRecht = true;
		benutzerListe = afdbBl.getBenutzerMitSchreibRecht(schreibRecht);
		for(Benutzer benutzer : benutzerListe)
		{
			cbZugewiesen.addItem(benutzer.getBenutzername());
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbZugewiesen.getModel().setSelectedItem(a.getZugewiesenAn());
	}
	
	private void initializecbKunde(Anforderung a)
	{
		List<Kunde> kundenListe = afdbBl.getAllKunden();
		for(Kunde kunde : kundenListe)
		{
			cbKunde.addItem(kunde);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbKunde.getModel().setSelectedItem(a.getKunde());
	}
	
	private void initializecbAnsprechperson(Anforderung a)
	{
		List<Benutzer> ansprechPersionListe = afdbBl.getAllAnsprechpersonen();
		for(Benutzer ap : ansprechPersionListe)
		{
			cbAnsprechperson.addItem(ap);
		}
		// angezeigt/ausgewaehlt sein sollen die Daten von der aktuell zu bearbeitenden Anforderung
		cbAnsprechperson.getModel().setSelectedItem(a.getAnsprechPerson());
	}
	
	private boolean pruefePflichtfelder() {
		boolean befuellt = true;
		lblTitel.setForeground(Color.BLACK);
		lblBeschreibung.setForeground(Color.BLACK);
		lblHelpdesknummer.setForeground(Color.BLACK);
		
		if(tfTitel.getText().isEmpty())
		{
			lblTitel.setForeground(Color.RED);
			befuellt = false;
		}
		if(taBeschreibung.getText().isEmpty())
		{
			lblBeschreibung.setForeground(Color.RED);
			befuellt = false;
		}
		if(!befuellt)
		{
			return false;
		}
		return true;
	}
	
	private Benutzer getBenutzerVonUsername()
	{
		Benutzer zugewiesenAn = (Benutzer) cbZugewiesen.getSelectedItem();
		return zugewiesenAn;
	}

	/**
	 * Die Methode speichert die bearbeiteten Daten der aktuellen Anforderung
	 */
	private void createAfdb() {
		String titel = tfTitel.getText();
		String beschreibung = taBeschreibung.getText();
		Benutzer benutzer = getBenutzerVonUsername();
		Date erfDatum = new Date();
		Benutzer ansprPers = (Benutzer) cbAnsprechperson.getSelectedItem();
		Kunde kd = (Kunde) cbKunde.getSelectedItem();
		AnforderungsArt anfArt = (AnforderungsArt) cbAnforderungsArt.getSelectedItem();
		Prioritaet prio = (Prioritaet) cbPrio.getSelectedItem();
		Status status = (Status) cbStatus.getSelectedItem();
		//zugewiesen an = anlegeBenutzer
		Modul modul = (Modul) cbModul.getSelectedItem();
		Version version = (Version) cbVersion.getSelectedItem();
		
		String hdNr = tfHelpdesknummer.getText();
		//if(hdNr.isEmpty()) {
		//	hdNr="0";
		//}
		String aufwand = tfAufwand.getText();
		if(aufwand.isEmpty()) {
			aufwand = "0";
		}
		float aufwandGesch = 0;
		try {
			aufwandGesch = Float.parseFloat(aufwand);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,"Aufwand Wert: "+aufwand +" ist nicht gueltig. Richtiges Format: 1.2");
			return;
		}
		Date fertigStellGepl = dcFertigStellGepl.getDate();
		if(fertigStellGepl == null) {
			fertigStellGepl = new Date();
		}
		//fertigStellIst noch implementieren
		Date fertigStellIst = null; //new Date();
		String verwAnforderungen = tfVerwAnf.getText();
		if(verwAnforderungen.isEmpty()) {
			verwAnforderungen="";
		}
		String schluesselBegriffe = tfSchluesselbegriffe.getText();
		if(schluesselBegriffe.isEmpty()) {
			schluesselBegriffe = "";
		}
		
		//---------------------------
		// updaten des aktuellen Anforderungs-Objektes mit den (moeglicherweise) neuen Daten:
		//---------------------------
		
		boolean speicherung = false;
		// wenn Anhang ausgewaehlt, dann die Methode mit Anhang aufrufen
		if (frame.filepath.length() > 0){
			System.out.println("filepath length: "+frame.filepath.length());
			// Anhang hinzufuegen:
			Anhang anh = new Anhang();
			anh.setName(file.getName());
			anh.setHinzugefuegtAm(new Date());
			anh.setFile(file);
			
			// die createAfdb Methode mit Anhang aufrufen:
			speicherung = afdbBl.persistAfdb(anf, anh, titel, beschreibung, benutzer, erfDatum, ansprPers, kd, anfArt, prio, status, benutzer, modul, version, hdNr,
					aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);			
		} // sonst (wenn kein Anhang), dann die normale Methode aufrufen
		else {
			// die createAfdb Methode ohne Anhang aufrufen:
			speicherung = afdbBl.persistAfdb(anf, titel, beschreibung, benutzer, erfDatum, ansprPers, kd, anfArt, prio, status, benutzer, modul, version, hdNr,
				aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);
		}		

		if(speicherung)
		{
			JOptionPane.showMessageDialog(this,"Speicherung erfolgreich!");
			//todo
			//System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(this,"Speicherung nicht erfolgreich!");
			//todo
			System.exit(0);
		}

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Anforderung getAnf() {
		return anf;
	}

	public void setAnf(Anforderung anf) {
		this.anf = anf;
	}
	
	
	
	

	
	
	
}
