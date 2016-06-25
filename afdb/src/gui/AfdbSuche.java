package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import bl.AfdbSuchen;
import data.*;

import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

public class AfdbSuche extends JFrame {

	private JPanel contentPane;
	private JTextField tfAnfid;
	private JTextField tfTitel;
	private JTextField tfKunde;
	private JTextField tfVerwandteAnf;
	private JTextField tfSchluesselbegriffe;
	
	private JComboBox cbStatus;
	private JComboBox cbZugewiesen;
	private JScrollPane scrollPane;

	private AfdbSuchen afdbSuchen = new AfdbSuchen();
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfdbSuche frame = new AfdbSuche();
					frame.setVisible(true);
					/*
					frame.setBounds(300, 100, 1000, 600);
					frame.setMinimumSize(new Dimension(1100, 700));
					*/
					frame.initializeStatus();
					frame.initializeZugewiesen();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// ComboBox Status bef�llen
	private void initializeStatus()
	{
		cbStatus.addItem("");
		List<Status> statusListe = afdbSuchen.getAllStatus();
		for(Status status : statusListe)
		{
			
			cbStatus.addItem(status);
		}
	}
	
	// ComboBox Zugewiesen bef�llen
	private void initializeZugewiesen()
	{
		cbZugewiesen.addItem("");
		boolean schreibRecht = true;
		List<Benutzer> benutzerListe = afdbSuchen.getBenutzerMitSchreibRecht(schreibRecht);
		for(Benutzer benutzer : benutzerListe)
		{
			cbZugewiesen.addItem(benutzer.getBenutzername());
		}
	}

	/**
	 * Create the frame.
	 */
	public AfdbSuche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenu menu = new JMenu("Mir zugewiesen");
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("Bearbeiten");
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("Suchen");
		menuBar.add(menu_2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {120, 150, 100, 100};
		gbl_panel_2.rowHeights = new int[] {20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		panel_1.add(panel_2);
		
		JLabel lblAnfID = new JLabel("AnfID:");
		GridBagConstraints gbc_lblAnfID = new GridBagConstraints();
		gbc_lblAnfID.fill = GridBagConstraints.BOTH;
		gbc_lblAnfID.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnfID.gridx = 0;
		gbc_lblAnfID.gridy = 0;
		panel_2.add(lblAnfID, gbc_lblAnfID);
		
		tfAnfid = new JTextField();
		GridBagConstraints gbc_tfAnfid = new GridBagConstraints();
		gbc_tfAnfid.gridwidth = 3;
		gbc_tfAnfid.fill = GridBagConstraints.BOTH;
		gbc_tfAnfid.insets = new Insets(0, 0, 5, 0);
		gbc_tfAnfid.gridx = 1;
		gbc_tfAnfid.gridy = 0;
		panel_2.add(tfAnfid, gbc_tfAnfid);
		tfAnfid.setColumns(10);
		
		JLabel lblTitel = new JLabel("Titel:");
		GridBagConstraints gbc_lblTitel = new GridBagConstraints();
		gbc_lblTitel.anchor = GridBagConstraints.WEST;
		gbc_lblTitel.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitel.gridx = 0;
		gbc_lblTitel.gridy = 1;
		panel_2.add(lblTitel, gbc_lblTitel);
		
		tfTitel = new JTextField();
		tfTitel.setColumns(10);
		GridBagConstraints gbc_tfTitel = new GridBagConstraints();
		gbc_tfTitel.gridwidth = 3;
		gbc_tfTitel.insets = new Insets(0, 0, 5, 0);
		gbc_tfTitel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTitel.gridx = 1;
		gbc_tfTitel.gridy = 1;
		panel_2.add(tfTitel, gbc_tfTitel);
		
		JLabel lblKunde = new JLabel("Kunde:");
		GridBagConstraints gbc_lblKunde = new GridBagConstraints();
		gbc_lblKunde.anchor = GridBagConstraints.WEST;
		gbc_lblKunde.fill = GridBagConstraints.VERTICAL;
		gbc_lblKunde.insets = new Insets(0, 0, 5, 5);
		gbc_lblKunde.gridx = 0;
		gbc_lblKunde.gridy = 2;
		panel_2.add(lblKunde, gbc_lblKunde);
		
		tfKunde = new JTextField();
		tfKunde.setColumns(10);
		GridBagConstraints gbc_tfKunde = new GridBagConstraints();
		gbc_tfKunde.gridwidth = 3;
		gbc_tfKunde.insets = new Insets(0, 0, 5, 0);
		gbc_tfKunde.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKunde.gridx = 1;
		gbc_tfKunde.gridy = 2;
		panel_2.add(tfKunde, gbc_tfKunde);
		
		JLabel lblVerwandteAnf = new JLabel("Verwandte Anf.:");
		GridBagConstraints gbc_lblVerwandteAnf = new GridBagConstraints();
		gbc_lblVerwandteAnf.anchor = GridBagConstraints.WEST;
		gbc_lblVerwandteAnf.fill = GridBagConstraints.VERTICAL;
		gbc_lblVerwandteAnf.insets = new Insets(0, 0, 5, 5);
		gbc_lblVerwandteAnf.gridx = 0;
		gbc_lblVerwandteAnf.gridy = 3;
		panel_2.add(lblVerwandteAnf, gbc_lblVerwandteAnf);
		
		tfVerwandteAnf = new JTextField();
		tfVerwandteAnf.setColumns(10);
		GridBagConstraints gbc_tfVerwandteAnf = new GridBagConstraints();
		gbc_tfVerwandteAnf.gridwidth = 3;
		gbc_tfVerwandteAnf.insets = new Insets(0, 0, 5, 0);
		gbc_tfVerwandteAnf.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfVerwandteAnf.gridx = 1;
		gbc_tfVerwandteAnf.gridy = 3;
		panel_2.add(tfVerwandteAnf, gbc_tfVerwandteAnf);
		
		JLabel lblZugewiesen = new JLabel("Zugewiesen:");
		GridBagConstraints gbc_lblZugewiesen = new GridBagConstraints();
		gbc_lblZugewiesen.anchor = GridBagConstraints.WEST;
		gbc_lblZugewiesen.fill = GridBagConstraints.VERTICAL;
		gbc_lblZugewiesen.insets = new Insets(0, 0, 5, 5);
		gbc_lblZugewiesen.gridx = 0;
		gbc_lblZugewiesen.gridy = 4;
		panel_2.add(lblZugewiesen, gbc_lblZugewiesen);
		
		cbZugewiesen = new JComboBox();
		GridBagConstraints gbc_cbZugewiesen = new GridBagConstraints();
		gbc_cbZugewiesen.gridwidth = 3;
		gbc_cbZugewiesen.insets = new Insets(0, 0, 5, 0);
		gbc_cbZugewiesen.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbZugewiesen.gridx = 1;
		gbc_cbZugewiesen.gridy = 4;
		panel_2.add(cbZugewiesen, gbc_cbZugewiesen);
		
		JLabel lblStatus = new JLabel("Status:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.fill = GridBagConstraints.VERTICAL;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 5;
		panel_2.add(lblStatus, gbc_lblStatus);
		
		cbStatus = new JComboBox();
		GridBagConstraints gbc_cbStatus = new GridBagConstraints();
		gbc_cbStatus.gridwidth = 3;
		gbc_cbStatus.insets = new Insets(0, 0, 5, 0);
		gbc_cbStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbStatus.gridx = 1;
		gbc_cbStatus.gridy = 5;
		panel_2.add(cbStatus, gbc_cbStatus);
		
		JLabel lblSchlsselbegriffe = new JLabel("Schl\u00FCsselbegriffe:");
		GridBagConstraints gbc_lblSchlsselbegriffe = new GridBagConstraints();
		gbc_lblSchlsselbegriffe.anchor = GridBagConstraints.WEST;
		gbc_lblSchlsselbegriffe.fill = GridBagConstraints.VERTICAL;
		gbc_lblSchlsselbegriffe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchlsselbegriffe.gridx = 0;
		gbc_lblSchlsselbegriffe.gridy = 6;
		panel_2.add(lblSchlsselbegriffe, gbc_lblSchlsselbegriffe);
		
		tfSchluesselbegriffe = new JTextField();
		tfSchluesselbegriffe.setColumns(10);
		GridBagConstraints gbc_tfSchluesselbegriffe = new GridBagConstraints();
		gbc_tfSchluesselbegriffe.gridwidth = 3;
		gbc_tfSchluesselbegriffe.insets = new Insets(0, 0, 5, 0);
		gbc_tfSchluesselbegriffe.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSchluesselbegriffe.gridx = 1;
		gbc_tfSchluesselbegriffe.gridy = 6;
		panel_2.add(tfSchluesselbegriffe, gbc_tfSchluesselbegriffe);
		
		JButton btnSuchen = new JButton("Suchen");
		btnSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suchen();
			}
		});
		GridBagConstraints gbc_btnSuchen = new GridBagConstraints();
		gbc_btnSuchen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSuchen.insets = new Insets(0, 0, 5, 5);
		gbc_btnSuchen.gridx = 2;
		gbc_btnSuchen.gridy = 7;
		panel_2.add(btnSuchen, gbc_btnSuchen);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnAbbrechen = new GridBagConstraints();
		gbc_btnAbbrechen.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbbrechen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbbrechen.gridx = 3;
		gbc_btnAbbrechen.gridy = 7;
		panel_2.add(btnAbbrechen, gbc_btnAbbrechen);
		
		this.scrollPane = new JScrollPane((Component) null);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 9;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		//table = new JTable();
		
		
	}

	private void suchen()
	{
		// Selected Items
		int anfID = Integer.parseInt(this.tfAnfid.getText());
		String titel = this.tfTitel.getText();
		String kunde = this.tfKunde.getText();
		String verwandteAnf = this.tfVerwandteAnf.getText();
		//Benutzer zugewiesen = (Benutzer)this.cbZugewiesen.getSelectedItem();
		String zugewiesen = this.cbZugewiesen.getSelectedItem().toString();
		//Status status = (Status)this.cbStatus.getSelectedItem();
		String status = this.cbStatus.getSelectedItem().toString();
		String schluesselbegriffe = this.tfSchluesselbegriffe.getText();

		// Test: Check AnfID
		List<Anforderung> anforderungen = this.afdbSuchen.suchen(anfID,titel,kunde,verwandteAnf,zugewiesen,status,schluesselbegriffe);
		if(anforderungen.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Suche nicht erfolgreich!");	
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Suche erfolgreich!");
			this.anforderungenAnzeigen(anforderungen);
		}
	}
	
	private void anforderungenAnzeigen(List<Anforderung> anforderungen)
	{
		/*
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"AnfID", "Priorit�t", "Status", "Titel", "Kunde", "Gepl. Fertigstellung", "Helpdesknr."
			}
		));
		*/
		
		Object[][] data = { null };
		String[] columnNames = {"AnfID", "Priorit�t", "Status", "Titel", "Kunde", "Gepl. Fertigstellung", "Helpdesknr."};
		DefaultTableModel datamodel = new DefaultTableModel(data,columnNames);
		
		for (Anforderung anf : anforderungen)
		{
			//datamodel.addRow("Test");
		}
		
		this.table = new JTable(datamodel);
		this.scrollPane.setViewportView(table);
	}
}