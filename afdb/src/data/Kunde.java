package data;

public class Kunde {
	private int kundenId;
	private String kundennummer;
	private String bezeichnung;
	private Adresse adresse;
	private Benutzer kontaktPerson;
	private String mail;
	
	public Kunde()
	{
		
	}
	
	public Kunde(int kundenId, String kundennummer, String bezeichnung, Adresse adresse, Benutzer kontaktPerson,
			String mail) {
		this.kundenId = kundenId;
		this.kundennummer = kundennummer;
		this.bezeichnung = bezeichnung;
		this.adresse = adresse;
		this.kontaktPerson = kontaktPerson;
		this.mail = mail;
	}

	public int getKundenId() {
		return kundenId;
	}

	public void setKundenId(int kundenId) {
		this.kundenId = kundenId;
	}

	public String getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(String kundennummer) {
		this.kundennummer = kundennummer;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Benutzer getKontaktPerson() {
		return kontaktPerson;
	}

	public void setKontaktPerson(Benutzer kontaktPerson) {
		this.kontaktPerson = kontaktPerson;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	//nötig für ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
	
	

}
