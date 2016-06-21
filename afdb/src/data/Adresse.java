package data;

public class Adresse {
	private int adresseId;
	private String strasse;
	private int hausnr;
	private String hausnrzusatz;
	private int plz;
	private String ort;
	private String land;
	
	public Adresse()
	{
		
	}
	
	public Adresse(int adresseId, String strasse, int hausnr, String hausnrzusatz, int plz, String ort, String land) {
		this.adresseId = adresseId;
		this.strasse = strasse;
		this.hausnr = hausnr;
		this.hausnrzusatz = hausnrzusatz;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
	}

	public int getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(int adresseId) {
		this.adresseId = adresseId;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getHausnr() {
		return hausnr;
	}

	public void setHausnr(int hausnr) {
		this.hausnr = hausnr;
	}

	public String getHausnrzusatz() {
		return hausnrzusatz;
	}

	public void setHausnrzusatz(String hausnrzusatz) {
		this.hausnrzusatz = hausnrzusatz;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		return "Adresse [adresseId=" + adresseId + ", strasse=" + strasse + ", hausnr=" + hausnr + ", hausnrzusatz="
				+ hausnrzusatz + ", plz=" + plz + ", ort=" + ort + ", land=" + land + "]";
	}
	
	

}
