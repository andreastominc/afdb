package data;

public class AnforderungsArt {
	
	private int anforderungsArtId;
	private String bezeichnung;
	
	public AnforderungsArt() {
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
	public String toString() {
		return "AnforderungsArt [anforderungsArtId=" + anforderungsArtId + ", bezeichnung=" + bezeichnung + "]";
	}
	
	
	
	

}
