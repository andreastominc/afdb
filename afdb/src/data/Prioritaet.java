package data;

public class Prioritaet {
	private int prioritaetId;
	private String bezeichnung;
	
	public Prioritaet() {
	}

	public Prioritaet(int prioId, String bez) {
		this.prioritaetId = prioId;
		this.bezeichnung = bez;
	}
	
	public int getPrioritaetId() {
		return prioritaetId;
	}

	public void setPrioritaetId(int prioritaetsId) {
		this.prioritaetId = prioritaetsId;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	//n�tig f�r ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
		
	
	
	

}
