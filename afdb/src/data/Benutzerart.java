package data;

public class Benutzerart {
	private int benutzerArtId;
	private String bezeichnung;
	private boolean schreibRecht;
	
	public Benutzerart()
	{
		
	}
	
	public Benutzerart(int benutzerArtId, String bezeichnung, boolean schreibRecht) {
		this.benutzerArtId = benutzerArtId;
		this.bezeichnung = bezeichnung;
		this.schreibRecht = schreibRecht;
	}

	public int getBenutzerArtId() {
		return benutzerArtId;
	}

	public void setBenutzerArtId(int benutzerArtId) {
		this.benutzerArtId = benutzerArtId;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public boolean isSchreibRecht() {
		return schreibRecht;
	}

	public void setSchreibRecht(boolean schreibRecht) {
		this.schreibRecht = schreibRecht;
	}

	@Override
	public String toString() {
		return "Benutzerart [benutzerArtId=" + benutzerArtId + ", bezeichnung=" + bezeichnung + ", schreibRecht="
				+ schreibRecht + "]";
	}
	
	
	

}
