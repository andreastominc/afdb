package data;


public class Status {
	private int statusId;
	private String bezeichnung;
	
	
	
	public Status() {
	}
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	//nötig für ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
		
	
	
	

}
