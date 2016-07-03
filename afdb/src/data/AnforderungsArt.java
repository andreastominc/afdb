package data;

public class AnforderungsArt {
	
	private int anforderungsArtId;
	private String bezeichnung;
	
	public AnforderungsArt() {
	}
	
	public AnforderungsArt(int anfArtId, String bez) {
		this.anforderungsArtId = anfArtId;
		this.bezeichnung = bez;
	}

	public int getAnforderungsArtId() {
		return anforderungsArtId;
	}

	public void setAnforderungsArtId(int anforderungsArtId) {
		this.anforderungsArtId = anforderungsArtId;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	//nötig für ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
	
	
	
	

}
