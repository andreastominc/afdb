package dal;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data.AnforderungsArt;
import data.Benutzer;
import data.Kunde;
import data.Modul;
import data.Prioritaet;
import data.Status;
import data.Version;



public class QueryHelper {
	
	public QueryHelper() {
		
	}
	
	public static List<Status> getAllStatus() {
		Session session = HibernateUtil.session;
		
		List<Status> status = session.createQuery(" from Status").list();
		logData(status);
		
		return status;
	}
	
	public static List<Prioritaet> getAllPrioritaet() {
		Session session = HibernateUtil.session;
		
		List<Prioritaet> prioritaet = session.createQuery(" from Prioritaet").list();
		logData(prioritaet);
		
		return prioritaet;
	}
	
	public static List<Modul> getAllModul() {
		Session session = HibernateUtil.session;
		
		List<Modul> modul = session.createQuery(" from Modul").list();
		logData(modul);
		
		return modul;
	}
	
	public static List<AnforderungsArt> getAllAnforderungsArt() {
		Session session = HibernateUtil.session;
		
		List<AnforderungsArt> anforderungsArt = session.createQuery(" from AnforderungsArt").list();
		logData(anforderungsArt);
		
		return anforderungsArt;
	}
	
	public static List<Version> getAllVersion() {
		Session session = HibernateUtil.session;
		
		List<Version> version = session.createQuery(" from Version").list();
		logData(version);
		
		return version;
	}
	
	public static List<Benutzer> getBenutzerMitSchreibRecht(boolean schreibRecht) {
		Session session = HibernateUtil.session;
		
		String stmt = "select b from Benutzer b inner join b.art ba"+
					  " where ba.schreibRecht = :schreibrecht";
		List<Benutzer> benutzer = session.createQuery(stmt).setBoolean("schreibrecht", schreibRecht).list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Kunde> getAllKunden() {
		Session session = HibernateUtil.session;
	
		List<Kunde> benutzer = session.createQuery(" from Kunde").list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Benutzer> getAnsprechpersonVonKunde(Kunde kd) {
		Session session = HibernateUtil.session;
	
		String stmt = "select distinct ap from Kunde kd inner join kd.kontaktPerson ap "+
				  " where kd.kundenId= :id";
		List<Benutzer> benutzer = session.createQuery(stmt).setInteger("id", kd.getKundenId()).list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Benutzer> getAllAnsprechpersonen() {
		Session session = HibernateUtil.session;
	
		List<Benutzer> benutzer = session.createQuery("select distinct ap from Kunde kd inner join kd.kontaktPerson ap").list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static Kunde getKundeVonBezeichnung(String bezeichnung) throws Exception {
		Session session = HibernateUtil.session;
	
		List<Kunde> kdList = session.createQuery(" from Kunde where bezeichnung= :bez").setString("bez", bezeichnung).list();
		
		logData(kdList);
		
		if(kdList.size()>1)
		{
			throw new Exception("Mehr als ein Kunde mit selber Bezeichnung");
		}
		
		return kdList.get(0);
	}
	
	
	
	private static void logData(List liste)
	{
		for(int i = 0; i<liste.size(); i++)
		{
			System.out.println(liste.get(i).toString());
		}
	}
	
	

}
