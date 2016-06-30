package bl;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import data.Anforderung;

public class AfdbJTableModel extends AbstractTableModel{
	
	public String[] columnNames = {"AnfID", "Priorität", "Status", "Titel", "Kunde", "Gepl. Fertigstellung", "Helpdesknr."};
	private ArrayList<Anforderung> anfList;
	
    public AfdbJTableModel() {
		// TODO Auto-generated constructor stub
    	this.anfList = new ArrayList<Anforderung>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return anfList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	      switch (columnIndex) {
	      case 0:
	        return anfList.get(rowIndex).getAnfId();
	      case 1:
	        return anfList.get(rowIndex).getPrio().getBezeichnung();
	      case 2:
	        return anfList.get(rowIndex).getStatus().getBezeichnung();
	      case 3:
	        return anfList.get(rowIndex).getTitel();
	      case 4:
	        return anfList.get(rowIndex).getKunde().getBezeichnung();
	      case 5:
		    return anfList.get(rowIndex).getFertiggeplant();
	      case 6:
	    	return anfList.get(rowIndex).getHdNummer();
	      }

	      return new String();
	}
	
    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
    
    public void removeRow(int row) {
        // remove a row from your internal data structure
    	anfList.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void addRow(Anforderung rowData)
    {
        anfList.add(rowData);
        fireTableRowsInserted(anfList.size() - 1, anfList.size() - 1);
    }

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
    
    public Anforderung getSelectedRow(int selectedRow) {
        return anfList.get(selectedRow);
    }
    
    

}
