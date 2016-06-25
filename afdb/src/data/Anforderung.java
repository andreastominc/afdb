package data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Anforderung {
	private int anfId;
	private String titel;
	private String beschreibung;
	private Benutzer angelegtVon;
	private Date erfassungsDatum;
	private Benutzer ansprechPerson;
	private Kunde kunde;
	private AnforderungsArt anfArt;
	private Prioritaet prio;
	private Status status;
	private Benutzer zugewiesenAn;
	private Modul modul;
	private Version version;
	private float aufwandGeschaetzt;
	private Date fertiggeplant;
	private Date fertigIst;
	private String hdNummer;
	private String schluesselBegriffe;

	
	public Anforderung(String titel, String beschreibung, Benutzer benutzer, Date erfDatum, Benutzer ansprPers, Kunde kd, AnforderungsArt anfArt, Prioritaet prio, Status status, Benutzer zugBenutzer, Modul modul, Version version, String hdNr, float aufwandGesch, Date fertigStellGepl, Date fertigStellIst, String schluesselBegriffe) {
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.angelegtVon = benutzer;
		this.erfassungsDatum = erfDatum;
		this.ansprechPerson = ansprPers;
		this.kunde = kd;
		this.anfArt = anfArt;
		this.prio = prio;
		this.status = status;
		this.zugewiesenAn = zugBenutzer;
		this.modul = modul;
		this.version = version;
		this.hdNummer = hdNr;
		this.aufwandGeschaetzt = aufwandGesch;
		this.fertiggeplant = fertigStellGepl;
		this.fertigIst = fertigStellIst;
		this.schluesselBegriffe = schluesselBegriffe;
	}
	
	public Anforderung()
	{
		
	}

	public int getAnfId() {
		return anfId;
	}

	public void setAnfId(int anfId) {
		this.anfId = anfId;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Benutzer getAngelegtVon() {
		return angelegtVon;
	}

	public void setAngelegtVon(Benutzer angelegtVon) {
		this.angelegtVon = angelegtVon;
	}

	public Date getErfassungsDatum() {
		return erfassungsDatum;
	}

	public void setErfassungsDatum(Date erfassungsDatum) {
		this.erfassungsDatum = erfassungsDatum;
	}

	public Benutzer getAnsprechPerson() {
		return ansprechPerson;
	}

	public void setAnsprechPerson(Benutzer ansprechPerson) {
		this.ansprechPerson = ansprechPerson;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public AnforderungsArt getAnfArt() {
		return anfArt;
	}

	public void setAnfArt(AnforderungsArt anfArt) {
		this.anfArt = anfArt;
	}

	public Prioritaet getPrio() {
		return prio;
	}

	public void setPrio(Prioritaet prio) {
		this.prio = prio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Benutzer getZugewiesenAn() {
		return zugewiesenAn;
	}

	public void setZugewiesenAn(Benutzer zugewiesenAn) {
		this.zugewiesenAn = zugewiesenAn;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getHdNummer() {
		return hdNummer;
	}

	public void setHdNummer(String hdNummer) {
		this.hdNummer = hdNummer;
	}

	public float getAufwandGeschaetzt() {
		return aufwandGeschaetzt;
	}

	public void setAufwandGeschaetzt(float aufwandGeschaetzt) {
		this.aufwandGeschaetzt = aufwandGeschaetzt;
	}

	public Date getFertiggeplant() {
		return fertiggeplant;
	}

	public void setFertiggeplant(Date fertiggeplant) {
		this.fertiggeplant = fertiggeplant;
	}

	public Date getFertigIst() {
		return fertigIst;
	}

	public void setFertigIst(Date fertigIst) {
		this.fertigIst = fertigIst;
	}

	public String getSchluesselBegriffe() {
		return schluesselBegriffe;
	}

	public void setSchluesselBegriffe(String schluesselBegriffe) {
		this.schluesselBegriffe = schluesselBegriffe;
	}

	@Override
	public String toString() {
		return "Anforderung [anfId=" + anfId + ", titel=" + titel + ", beschreibung=" + beschreibung + ", angelegtVon="
				+ angelegtVon + ", erfassungsDatum=" + erfassungsDatum + ", ansprechPerson=" + ansprechPerson
				+ ", kunde=" + kunde + ", anfArt=" + anfArt + ", prio=" + prio + ", status=" + status
				+ ", zugewiesenAn=" + zugewiesenAn + ", modul=" + modul + ", version=" + version + ", hdNummer="
				+ hdNummer + ", aufwandGeschaetzt=" + aufwandGeschaetzt + ", fertiggeplant=" + fertiggeplant
				+ ", fertigIst=" + fertigIst + ", auftragsnummer="  + ", schluesselBegriffe="
				+ schluesselBegriffe + "]";
	}

	

}
