package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AfdbFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfKunde;
	private JTextField tfAnsprechpersion;
	private JTextField tfHelpdesknummer;
	private JTextField tfTitel;
	private JTextField tfKopieAn;
	private JTextField tfVerwAnf;
	private JTextField tfSchluesselbegriffe;
	private JTextField textField;
	private JTextField tfGespAnhaenge;
	private JTextField tfAufwand;
	private JTextField tfGeplFertigstellung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfdbFrame frame = new AfdbFrame();
					frame.setVisible(true);
					frame.setBounds(300, 100, 1000, 600);
					frame.setMinimumSize(new Dimension(1100, 700));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AfdbFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenu mnMirZugewiesen = new JMenu("Mir zugewiesen");
		menuBar.add(mnMirZugewiesen);
		
		JMenu mnBearbeiten = new JMenu("Bearbeiten");
		menuBar.add(mnBearbeiten);
		
		JMenu mnSuchen = new JMenu("Suchen");
		menuBar.add(mnSuchen);
		
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
		
		JLabel lblTitel = new JLabel("Titel:");
		panel_9.add(lblTitel);
		
		JLabel lblHelpdesknummer = new JLabel("Helpdesknummer:");
		panel_9.add(lblHelpdesknummer);
		
		JLabel lblBeschreibung = new JLabel("Beschreibung:");
		panel_9.add(lblBeschreibung);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(8, 1, 0, 0));
		
		tfKunde = new JTextField();
		panel_6.add(tfKunde);
		tfKunde.setColumns(10);
		
		tfAnsprechpersion = new JTextField();
		panel_6.add(tfAnsprechpersion);
		tfAnsprechpersion.setColumns(10);
		
		JComboBox cbAnforderungsArt = new JComboBox();
		panel_6.add(cbAnforderungsArt);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));
		
		JComboBox cbPrio = new JComboBox();
		panel_7.add(cbPrio);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(JLabel.RIGHT);
		lblStatus.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		panel_7.add(lblStatus);
		
		JComboBox cbStatus = new JComboBox();
		panel_7.add(cbStatus);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 3, 0, 0));
		
		JComboBox cbVersion = new JComboBox();
		panel_8.add(cbVersion);
		
		JLabel lblModul = new JLabel("Modul:");
		lblModul.setHorizontalAlignment(JLabel.RIGHT);
		lblModul.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		panel_8.add(lblModul);
		
		JComboBox cbModul = new JComboBox();
		panel_8.add(cbModul);
		
		tfHelpdesknummer = new JTextField();
		panel_6.add(tfHelpdesknummer);
		tfHelpdesknummer.setColumns(10);
		
		tfTitel = new JTextField();
		panel_6.add(tfTitel);
		tfTitel.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JTextArea taBeschreibung = new JTextArea();
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
		
		JLabel lblAnhnge = new JLabel("Anh\u00E4nge:");
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
		
		tfGeplFertigstellung = new JTextField();
		panel_12.add(tfGeplFertigstellung);
		tfGeplFertigstellung.setColumns(10);
		
		tfVerwAnf = new JTextField();
		panel_11.add(tfVerwAnf);
		tfVerwAnf.setColumns(10);
		
		tfSchluesselbegriffe = new JTextField();
		panel_11.add(tfSchluesselbegriffe);
		tfSchluesselbegriffe.setColumns(10);
		
		JComboBox cbZugewiesen = new JComboBox();
		panel_11.add(cbZugewiesen);
		
		JPanel panel_15 = new JPanel();
		panel_11.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_15.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btHinzufuegen = new JButton("Hinzuf\u00FCgen");
		btHinzufuegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		        JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
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
		panel_14.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		panel_14.add(btnAbbrechen);
	}

}
