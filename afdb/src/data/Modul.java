package data;

public class Modul {
	private int modulId;
	private String bezeichnung;
	
	public Modul()
	{
	}

	public int getModulId() {
		return modulId;
	}

	public void setModulId(int modulId) {
		this.modulId = modulId;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	//n�tig f�r ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
	
	
}
