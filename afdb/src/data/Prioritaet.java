package data;

public class Prioritaet {
	private int prioritaetId;
	private String bezeichnung;
	
	public Prioritaet() {
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

	@Override
	public String toString() {
		return "Prioritaet [prioritaetsId=" + prioritaetId + ", bezeichnung=" + bezeichnung + "]";
	}
	
	
	

}
