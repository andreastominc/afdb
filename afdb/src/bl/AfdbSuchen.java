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
	
	public boolean suchen(int AnfID)
	{
		boolean gefunden = false;
		try {
			List<Anforderung> anforderungen = QueryHelper.getAllAnforderungen();
			for (Anforderung anf : anforderungen )
			{
				if ((anf.getAnfId() == AnfID))
					gefunden = true;
			}
			return gefunden;
		} catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
