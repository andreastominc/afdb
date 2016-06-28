package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bl.AfdbHinzufuegen;
import data.Anforderung;

import javax.swing.JList;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
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
	public static List<Anforderung> selectedAnforderungen = new Vector<Anforderung>();
	private JPanel panel_2;
	private JButton bt‹bernehmen;
	
	private static SelectVerwAnfFrame frame = new SelectVerwAnfFrame();

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
		
		bt‹bernehmen = new JButton("\u00DCbernehmen");
		bt‹bernehmen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AfdbHinzufuegenFrame.frame.setVisible(true);
				String text="";
				for(Anforderung anf : selectedAnforderungen)
				{
					text += anf.getAnfId()+",";
				}
				AfdbHinzufuegenFrame.tfVerwAnf.setText(text.substring(0, text.length()-1));
				
			}
		});
		panel_2.add(bt‹bernehmen, BorderLayout.EAST);

		
		
		
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
		allAnfTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setSelectedAnforderung();
				
			}

		
		});
		this.allAnfTable.setModel(datamodelAll);
		this.scrollPane.setViewportView(allAnfTable);
		
		this.selectedAnfTable = new JTable();
		datamodelSelected = new DefaultTableModel();
		datamodelSelected.setColumnIdentifiers(columnNames);
		this.selectedAnfTable.setModel(datamodelSelected);
		this.scrollPane_1.setViewportView(selectedAnfTable);
		
	}
	
	private void setSelectedAnforderung() {
		Anforderung anf = anforderungen.get(allAnfTable.getSelectedRow());
		selectedAnforderungen.add(anf);
		System.out.println(anf);
		datamodelSelected.addRow(new Object[]{anf.getAnfId()+"",anf.getTitel()});
		this.selectedAnfTable.setModel(datamodelSelected);
		this.scrollPane_1.setViewportView(selectedAnfTable);
		
		
	}

}
