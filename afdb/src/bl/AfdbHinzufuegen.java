package bl;

import java.io.File;
import java.util.Date;
import java.util.List;

import dal.QueryHelper;
import data.*;

public class AfdbHinzufuegen {
	
	private Anforderung anf;
	
	public AfdbHinzufuegen()
	{
		
	}
	
	public List<Prioritaet> getAllPrioritaeten()
	{
		return QueryHelper.getAllPrioritaet();
	}
	
	public List<Version> getAllVersionen()
	{
		return QueryHelper.getAllVersion();
	}
	
	public List<Modul> getAllModule()
	{
		return QueryHelper.getAllModul();
	}
	
	public List<Status> getAllStatus()
	{
		return QueryHelper.getAllStatus();
	}
	
	public List<AnforderungsArt> getAllAnforderungsart()
	{
		return QueryHelper.getAllAnforderungsArt();
	}
	
	public List<Anforderung> getAllAnforderungen()
	{
		return QueryHelper.getAllAnforderungen();
	}
	
	public List<Benutzer> getBenutzerMitSchreibRecht(boolean schreibRecht)
	{
		return QueryHelper.getBenutzerMitSchreibRecht(schreibRecht);
	}
	
	public List<Kunde> getAllKunden()
	{
		return QueryHelper.getAllKunden();
	}
	
	public List<Benutzer> getAllAnsprechpersonen()
	{
		return QueryHelper.getAllAnsprechpersonen();
	}
	
	public List<Benutzer> getAnsprechpersonVonKunde(Kunde kd)
	{
		return QueryHelper.getAnsprechpersonVonKunde(kd);
	}

	/**
	 * Anforderung speichern
	 */
	public boolean createAfdb(String titel, String beschreibung, Benutzer anlegeBenutzer, Date erfDatum, Benutzer ansprPers, Kunde kd, AnforderungsArt anfArt, Prioritaet prio, Status status, Benutzer zugBenutzer, Modul modul, Version version, String hdNr, float aufwandGesch, Date fertigStellGepl, Date fertigStellIst, String verwAnforderungen, String schluesselBegriffe) {
		anf = new Anforderung(titel, beschreibung, anlegeBenutzer, erfDatum, ansprPers, kd, anfArt, prio, status, zugBenutzer, modul, version, hdNr, aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);
		try {
			QueryHelper.saveAnf(anf);
			return true;
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Anforderung und Anhang speichern
	 */
	public boolean createAfdb(Anhang anh, String titel, String beschreibung, Benutzer anlegeBenutzer, Date erfDatum, Benutzer ansprPers, Kunde kd, AnforderungsArt anfArt, Prioritaet prio, Status status, Benutzer zugBenutzer, Modul modul, Version version, String hdNr, float aufwandGesch, Date fertigStellGepl, Date fertigStellIst, String verwAnforderungen, String schluesselBegriffe) {
		anf = new Anforderung(titel, beschreibung, anlegeBenutzer, erfDatum, ansprPers, kd, anfArt, prio, status, zugBenutzer, modul, version, hdNr, aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe);
		try {
			QueryHelper.saveAnf(anf, anh);
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
