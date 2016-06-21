package bl;

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
	
	public Kunde getKundeVonBezeichnung(String bezeichnung)
	{
		try {
			return QueryHelper.getKundeVonBezeichnung(bezeichnung);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Kunde();
	}

}
