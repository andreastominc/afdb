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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import bl.AfdbHinzufuegen;
import javax.swing.JSeparator;

public class AfdbHinzufuegenFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitel;
	private JTextField tfHelpdesknummer;
	private JTextField tfKopieAn;
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
	private AfdbHinzufuegen afdbBl;
	private List<Benutzer> ansprPersListe;
	private List<Benutzer> benutzerListe;
	
	public static AfdbHinzufuegenFrame frame; // als private static definieren, damit spaeter "frame.dispose" aufgerufen werden kann.
	public static JTextField tfVerwAnf;
	private ArrayList<Anforderung> selAnf = new ArrayList<Anforderung>();
	
	private Benutzer eingeloggterUser;
	private JLabel lblEingeloggterUser;
		
	private void initializeData() {
		initializeCbStatus();
		initializeCbModul();
		initializeCbPrio();
		initializeCbAnforderungsArt();
		initializeCbVersion();
		initializecbZugewiesen();
		initializecbKunde();
		//initializecbAnsprechperson();
	}

	/**
	 * Create the frame.
	 */
	public AfdbHinzufuegenFrame() {
		setTitle("Anforderung hinzufuegen");
		frame = this;
		frame.setLocationRelativeTo(null);
		
		this.afdbBl = new AfdbHinzufuegen();
		
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
				
				frame.dispose(); // aktuelles Frame schliessen
				openZugewiesenFrame();
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
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		menuBar.add(mntmBearbeiten);
		
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
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 15); // 15 hinzufuegen zum aktuellen als default Wert
		dcFertigStellGepl.setDate(cal.getTime());
		panel_16.add(dcFertigStellGepl);
		
		JPanel panel_17 = new JPanel();
		panel_11.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		tfVerwAnf = new JTextField();
		tfVerwAnf.setColumns(10);
		panel_17.add(tfVerwAnf, BorderLayout.CENTER);
		
		JButton btnVerwAnfHinzufuegen = new JButton("verw. Anf. hinzufuegen");
		btnVerwAnfHinzufuegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SelectVerwAnfFrame selected = new SelectVerwAnfFrame();
				selected.setHZF(frame);
				selected.setLocationRelativeTo(null);  
				selected.setVisible(true);
			}
		});
		panel_17.add(btnVerwAnfHinzufuegen, BorderLayout.EAST);
		
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
		
		JButton btHinzufuegen = new JButton("Anhang hinzufuegen");
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
		
		JButton btnSpeichern = new JButton("Anforderung erstellen");
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
				frame.dispose();
				openZugewiesenFrame();
			}
		});
		panel_14.add(btnAbbrechen);
		
		initializeData();
	}
	
	private void initializeCbStatus()
	{
		List<Status> statusListe = afdbBl.getAllStatus();
		for(Status status : statusListe)
		{
			cbStatus.addItem(status);
		}

	}
	
	private void initializeCbModul()
	{
		List<Modul> modulListe = afdbBl.getAllModule();
		for(Modul modul : modulListe)
		{
			cbModul.addItem(modul);
		}

	}
	
	private void initializeCbAnforderungsArt()
	{
		List<AnforderungsArt> anfArtListe =  afdbBl.getAllAnforderungsart();
		for(AnforderungsArt anfArt : anfArtListe)
		{
			cbAnforderungsArt.addItem(anfArt);
		}

	}
	
	private void initializeCbPrio()
	{
		List<Prioritaet> prioListe = afdbBl.getAllPrioritaeten();
		for(Prioritaet prio : prioListe)
		{
			cbPrio.addItem(prio);
		}

	}
	
	private void initializeCbVersion()
	{
		List<Version> versionListe = afdbBl.getAllVersionen();
		for(Version version : versionListe)
		{
			cbVersion.addItem(version);
		}

	}

	private void initializecbZugewiesen()
	{
		boolean schreibRecht = true;
		benutzerListe = afdbBl.getBenutzerMitSchreibRecht(schreibRecht);
		for(Benutzer benutzer : benutzerListe)
		{
			cbZugewiesen.addItem(benutzer);
		}

	}
	
	private void initializecbKunde()
	{
		List<Kunde> kundenListe = afdbBl.getAllKunden();
		for(Kunde kunde : kundenListe)
		{
			cbKunde.addItem(kunde);
		}
	}
	
	private void initializecbAnsprechperson()
	{
		List<Benutzer> ansprechPersionListe = afdbBl.getAllAnsprechpersonen();
		for(Benutzer ap : ansprechPersionListe)
		{
			cbAnsprechperson.addItem(ap);
		}
		
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
	
	private Benutzer getBenutzerVonUsername(){
		Benutzer zugewiesenAn = (Benutzer) cbZugewiesen.getSelectedItem();
		return zugewiesenAn;
	}

	private void createAfdb() {
		
		for(Anforderung a2 : selAnf){
			System.out.println("-----selAnf="+a2.toString());
		}
		
		String titel = tfTitel.getText();
		String beschreibung = taBeschreibung.getText();
		Benutzer zugBenutzer = getBenutzerVonUsername();
		Date erfDatum = new Date();
		Benutzer ansprPers = (Benutzer) cbAnsprechperson.getSelectedItem();
		Kunde kd = (Kunde) cbKunde.getSelectedItem();
		AnforderungsArt anfArt = (AnforderungsArt) cbAnforderungsArt.getSelectedItem();
		Prioritaet prio = (Prioritaet) cbPrio.getSelectedItem();
		Status status = (Status) cbStatus.getSelectedItem();
		Benutzer anlegeBenutzer = getEingeloggterUser();
		Modul modul = (Modul) cbModul.getSelectedItem();
		Version version = (Version) cbVersion.getSelectedItem();
		
		String hdNr = tfHelpdesknummer.getText();
		if(hdNr.isEmpty())
		{
			hdNr="0";
		}
		String aufwand = tfAufwand.getText();
		if(aufwand.isEmpty())
		{
			aufwand = "0";
		}
		float aufwandGesch = 0;
		try {
		aufwandGesch = Float.parseFloat(aufwand);
		} catch (NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(this,"Aufwand Wert: "+aufwand +" ist nicht gueltig. Richtiges Format: 1.2");
			return;
		}
		Date fertigStellGepl = dcFertigStellGepl.getDate();
		if(fertigStellGepl == null)
		{
			fertigStellGepl = new Date();
		}
		//fertigStellIst noch implementieren
		Date fertigStellIst = null; //new Date();
		String verwAnforderungen = tfVerwAnf.getText();
		if(verwAnforderungen.isEmpty())
		{
			verwAnforderungen="";
		}
		String schluesselBegriffe = tfSchluesselbegriffe.getText();
		if(schluesselBegriffe.isEmpty())
		{
			schluesselBegriffe = "";
		}
		
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
			speicherung = afdbBl.createAfdb(anh, titel, beschreibung, anlegeBenutzer, erfDatum, ansprPers, kd, anfArt, prio, status,zugBenutzer, modul, version, hdNr,
					aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);			
		} // sonst (wenn kein Anhang), dann die normale Methode aufrufen
		else {
			// die createAfdb Methode ohne Anhang aufrufen:
			speicherung = afdbBl.createAfdb(titel, beschreibung, anlegeBenutzer, erfDatum, ansprPers, kd, anfArt, prio, status, zugBenutzer, modul, version, hdNr,
				aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);
		}		

		if(speicherung)
		{
			JOptionPane.showMessageDialog(this,"Speicherung erfolgreich!");
			frame.dispose(); // aktuelles Frame schliessen
			AfdbSuchenFrame suche_frame = new AfdbSuchenFrame(afdbBl.getAnf());
			suche_frame.setEingeloggterUser(eingeloggterUser);
			suche_frame.setBounds(300, 100, 1000, 600);
			suche_frame.setMinimumSize(new Dimension(1100, 700));
			suche_frame.initializeData();
			suche_frame.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this,"Speicherung nicht erfolgreich!");
		}

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ArrayList<Anforderung> getSelAnf() {
		return selAnf;
	}

	public void setSelAnf(ArrayList<Anforderung> selAnf) {
		this.selAnf = selAnf;
	}

	public Benutzer getEingeloggterUser() {
		return eingeloggterUser;
	}

	public void setEingeloggterUser(Benutzer eingeloggterUser) {
		this.eingeloggterUser = eingeloggterUser;
		this.lblEingeloggterUser.setText(eingeloggterUser.getBenutzername());
	}

	private void openZugewiesenFrame() {
		AfdbZugewiesenFrame zugew_frame = new AfdbZugewiesenFrame();
		zugew_frame.setEingeloggterUser(eingeloggterUser);
		zugew_frame.initializeData();
		zugew_frame.setBounds(300, 100, 1000, 600);
		zugew_frame.setMinimumSize(new Dimension(1100, 700));
		zugew_frame.setVisible(true); // das "Mir zugewiesen"-Frame oeffnen und anzeigen
	}
}
