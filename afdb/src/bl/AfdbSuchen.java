package bl;

import java.util.List;
import dal.QueryHelper;
import data.*;

public class AfdbSuchen {

	public AfdbSuchen()
	{
		
	}
	
	public List<Status> getAllStatus()
	{
		return QueryHelper.getAllStatus();
	}
	
	public List<Benutzer> getBenutzerMitSchreibRecht(boolean schreibRecht)
	{
		return QueryHelper.getBenutzerMitSchreibRecht(schreibRecht);
	}
	
	public List<Anforderung> suchen(int anfID, String titel, String kunde, String verwandteAnf, String zugewiesen, String status, String schluesselbegriffe)
	{
		List<Anforderung> anforderungen = QueryHelper.getFilteredAnforderungen(anfID, titel, kunde, verwandteAnf, zugewiesen, status, schluesselbegriffe);
		return anforderungen;
		
	}
}
