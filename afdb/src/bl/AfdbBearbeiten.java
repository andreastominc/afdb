package bl;

import java.io.File;
import java.util.Date;
import java.util.List;

import dal.QueryHelper;
import data.*;

public class AfdbBearbeiten {
	
	private Anforderung anf;
	
	public AfdbBearbeiten(){
	}
	
	public List<Prioritaet> getAllPrioritaeten(){
		return QueryHelper.getAllPrioritaet();
	}
	
	public List<Version> getAllVersionen(){
		return QueryHelper.getAllVersion();
	}
	
	public List<Modul> getAllModule(){
		return QueryHelper.getAllModul();
	}
	
	public List<Status> getAllStatus(){
		return QueryHelper.getAllStatus();
	}
	
	public List<AnforderungsArt> getAllAnforderungsart(){
		return QueryHelper.getAllAnforderungsArt();
	}
	
	public List<Anforderung> getAllAnforderungen(){
		return QueryHelper.getAllAnforderungen();
	}
	
	public List<Benutzer> getBenutzerMitSchreibRecht(boolean schreibRecht){
		return QueryHelper.getBenutzerMitSchreibRecht(schreibRecht);
	}
	
	public List<Kunde> getAllKunden(){
		return QueryHelper.getAllKunden();
	}
	
	public List<Benutzer> getAllAnsprechpersonen(){
		return QueryHelper.getAllAnsprechpersonen();
	}
	
	public List<Benutzer> getAnsprechpersonVonKunde(Kunde kd){
		return QueryHelper.getAnsprechpersonVonKunde(kd);
	}
	
	/**
	 * Speichert einen Anhang zu einer bestehenden Anforderung
	 * @param anf
	 * @param anh
	 * @return
	 */
	public boolean persistAnhang(Anforderung anf, Anhang anh){
		System.out.println("----persistAnhang");
		
		try {
			QueryHelper.updateAnf(anf, anh);
			return true;
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * (geaenderte) Daten der Anforderung speichern
	 * to do ... funktioniert noch nicht
	 */
	public boolean persistAfdb(Anforderung anf, String titel, String beschreibung, Benutzer benutzer, Date erfDatum, Benutzer ansprPers, Kunde kd, AnforderungsArt anfArt, Prioritaet prio, Status status, Benutzer benutzer2, Modul modul, Version version, String hdNr, float aufwandGesch, Date fertigStellGepl, Date fertigStellIst, String verwAnforderungen, String schluesselBegriffe) {
		System.out.println("----persistAfdb ohne Anhang");
		anf.setAnfArt(anfArt);
		anf.setAngelegtVon(benutzer);
		anf.setTitel(titel);
		anf.setBeschreibung(beschreibung);
		anf.setErfassungsDatum(erfDatum);
		anf.setAnsprechPerson(ansprPers);
		anf.setKunde(kd);
		anf.setPrio(prio);
		anf.setStatus(status);
		anf.setModul(modul);
		anf.setVersion(version);
		anf.setHdNummer(hdNr);
		anf.setAufwandGeschaetzt(aufwandGesch);
		anf.setFertiggeplant(fertigStellGepl);
		anf.setFertigIst(fertigStellIst);
		anf.setSchluesselBegriffe(schluesselBegriffe);
						
		try {
			QueryHelper.updateAnf(anf);
			return true;
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Anforderung getAnf() {
		return anf;
	}

	public void setAnf(Anforderung anf) {
		this.anf = anf;
	}
	
	


}
