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
	public String toString() {
		return "Modul [modulId=" + modulId + ", bezeichnung=" + bezeichnung + "]";
	}
	
	
}
