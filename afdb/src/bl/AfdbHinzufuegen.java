package bl;

import java.util.Date;
import java.util.List;

import dal.QueryHelper;
import data.*;

public class AfdbHinzufuegen {
	
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

	public boolean createAfdb(String titel, String beschreibung, Benutzer benutzer, Date erfDatum, Benutzer ansprPers, Kunde kd, AnforderungsArt anfArt, Prioritaet prio, Status status, Benutzer benutzer2, Modul modul, Version version, String hdNr, float aufwandGesch, Date fertigStellGepl, Date fertigStellIst, String schluesselBegriffe) {
		Anforderung anf = new Anforderung(1, titel, beschreibung, benutzer, erfDatum, ansprPers, kd, anfArt, prio, status, benutzer, modul, version, hdNr, aufwandGesch, fertigStellGepl, fertigStellIst,schluesselBegriffe);
		QueryHelper.saveAnf(anf);
		return true;
	}


}
