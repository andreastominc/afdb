package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bl.AfdbHinzufuegen;
import data.Anforderung;

import javax.swing.JList;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import javax.swing.JButton;

public class SelectVerwAnfFrame extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel datamodelAll;
	private DefaultTableModel datamodelSelected;
	
	private AfdbHinzufuegen afdbBl;
	private Panel panel;
	private JTable allAnfTable;
	private JTable selectedAnfTable;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private Panel panel_1;
	private JLabel lbAllAnf;
	private JLabel lbSelected;
	
	private List<Anforderung> anforderungen;
	private ArrayList<Anforderung> selectedAnforderungen = new ArrayList<Anforderung>();
	private JPanel panel_2;
	private JButton btUebernehmen;
	
	private static SelectVerwAnfFrame frame = new SelectVerwAnfFrame();
	private AfdbHinzufuegenFrame hzf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public SelectVerwAnfFrame() {
		setTitle("Verwandte Anforderungen auswählen");
		this.afdbBl = new AfdbHinzufuegen();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));;
		
		panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		lbAllAnf = new JLabel("Alle Anforderungen");
		panel_1.add(lbAllAnf);
		
		lbSelected = new JLabel("Verwandte Anforderungen");
		panel_1.add(lbSelected);
		
		panel = new Panel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		scrollPane =  new JScrollPane((Component) null);
		panel.add(scrollPane);
		
		scrollPane_1 = new JScrollPane((Component) null);
		panel.add(scrollPane_1);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		btUebernehmen = new JButton("\u00DCbernehmen");
		btUebernehmen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectedAnforderungen.size() > 0){ // nur was machen wenn tatsaechlich Anf. selektiert wurden
					String text = "";
					for (Anforderung anf : selectedAnforderungen) {
						text += anf.getAnfId() + ",";
					}
					AfdbHinzufuegenFrame.tfVerwAnf.setText(text.substring(0, text.length() - 1));
					
					// die selektierten Anforderung-Objekte wieder an den Hinzufuegen-Frame uebergeben:
					hzf.setSelAnf(selectedAnforderungen);
				}
				
				frame.dispose();
				AfdbHinzufuegenFrame.frame.setVisible(true);
			}
		});
		panel_2.add(btUebernehmen, BorderLayout.EAST);

		initializeTable();
	}
	
	private void initializeTable(){
		
		String[] columnNames = {"AnfID", "Titel"};
		datamodelAll = new DefaultTableModel();
		datamodelAll.setColumnIdentifiers(columnNames);
		anforderungen = afdbBl.getAllAnforderungen();
		for (Anforderung anf : anforderungen)
		{
			datamodelAll.addRow(new Object[]{anf.getAnfId()+"",anf.getTitel()});
		}
		
		this.allAnfTable = new JTable();
		allAnfTable.setAutoCreateRowSorter(true);
		allAnfTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// to do: dass es nur bei doppelklick uebernommen wird... geht aber nur mit abstracttablemodel.
				//if (arg0.getClickCount() == 2 && !arg0.isConsumed()) {
				//	arg0.consume();
					//verwandte Anforderung in Selektierte Liste hinzuf�gen
					setSelectedAnforderung();
				//}
			}

		
		});
		this.allAnfTable.setModel(datamodelAll);
		this.scrollPane.setViewportView(allAnfTable);
		
		this.selectedAnfTable = new JTable();
		selectedAnfTable.setAutoCreateRowSorter(true);
		datamodelSelected = new DefaultTableModel();
		datamodelSelected.setColumnIdentifiers(columnNames);
		
		selectedAnfTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//verwandte selektierte Anforderung in Gesamt List zur�ckgeben
				returnSelectedAnforderung();
			}
		});
		this.selectedAnfTable.setModel(datamodelSelected);
		this.scrollPane_1.setViewportView(selectedAnfTable);
		
		
	}
	
	private void setSelectedAnforderung() {
		//selektierte Anforderung von Gesamt Tabelle
		Anforderung anf = anforderungen.get(allAnfTable.getSelectedRow());
		
		//Anforderung zu Selektierte Liste hinzuf�gen und aus Gesamt List entfernen
		selectedAnforderungen.add(anf);
		anforderungen.remove(anf);
		
		System.out.println(anf);
		System.out.println("--");
		
		//Erstellen der Tabellen mit neuen Anforderungen
		sortAndBuildTables();		
	}
	
	private void returnSelectedAnforderung() {	
		//selektierte Anforderung von Select Tabelle
		Anforderung anf = selectedAnforderungen.get(selectedAnfTable.getSelectedRow());
		//Anforderung aus Selektierte Liste entfernen und zu Gesamt List hinzuf�gen
		selectedAnforderungen.remove(anf);
		anforderungen.add(anf);
		
		System.out.println(anf);
		System.out.println("--");
		
		//Erstellen der Tabellen mit neuen Anforderungen
		sortAndBuildTables();
	}
		
	//Tabelle nach �nderung neu erstellen - bei Sortierung gab es Probleme
	private DefaultTableModel updateDataModel(DefaultTableModel model, List<Anforderung> list)
	{
		String[] columnNames = {"AnfID", "Titel"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (Anforderung anf : list)
		{
			model.addRow(new Object[]{anf.getAnfId()+"",anf.getTitel()});
			System.out.println(anf);
		}
		return model;
	}
	
	
	//List mit Anforderungen nach Anforderungs-ID sortieren
	private void sortListByAnfId(List<Anforderung> list)
	{
		
		list.sort(new Comparator<Anforderung>() {

			@Override
	        public int compare(Anforderung o1, Anforderung o2)
	        {
	            return o1.getAnfId() - o2.getAnfId();
	        }

	    });
	}
	
	private void sortAndBuildTables(){
		//sotieren der Listen
		sortListByAnfId(anforderungen);
		sortListByAnfId(selectedAnforderungen);
		
		//neu aufbauen der Tabellen
		datamodelAll = updateDataModel(datamodelAll, anforderungen);
		datamodelSelected = updateDataModel(datamodelSelected, selectedAnforderungen);
		
		//neu erstellte Datamodels setzen
		this.allAnfTable.setModel(datamodelAll);
		this.scrollPane.setViewportView(allAnfTable);
		this.selectedAnfTable.setModel(datamodelSelected);
		this.scrollPane_1.setViewportView(selectedAnfTable);
	}
	
	

	public void setHZF(AfdbHinzufuegenFrame frame2) {
		hzf = frame2;
	}

}
