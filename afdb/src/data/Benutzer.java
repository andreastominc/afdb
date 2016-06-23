package data;

import java.util.Date;

public class Benutzer {
	private int benutzerId;
	private String vorname;
	private String nachname;
	private String benutzername;
	private String passwort;
	private Date anlegeDatum;
	private Date gueltigBis;
	private Benutzerart art;
	private String telefon;
	private String mail;
	
	public Benutzer()
	{
		
	}
	
	public Benutzer(int benutzerId, String vorname, String nachname, String benutzername, String passwort,
			Date anlegeDatum, Date gueltigBis, Benutzerart art, String telefon, String mail) {
		this.benutzerId = benutzerId;
		this.vorname = vorname;
		this.nachname = nachname;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.anlegeDatum = anlegeDatum;
		this.gueltigBis = gueltigBis;
		this.art = art;
		this.telefon = telefon;
		this.mail = mail;
	}

	public int getBenutzerId() {
		return benutzerId;
	}

	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public Date getAnlegeDatum() {
		return anlegeDatum;
	}

	public void setAnlegeDatum(Date anlegeDatum) {
		this.anlegeDatum = anlegeDatum;
	}

	public Date getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(Date gueltigBis) {
		this.gueltigBis = gueltigBis;
	}

	public Benutzerart getArt() {
		return art;
	}

	public void setArt(Benutzerart art) {
		this.art = art;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	//nötig für ComboBox Anzeige
	public String toString() {
		return vorname + " " +nachname;
	}
		
	
	

}
