package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

//import javax.management.Query;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.*;


public class QueryHelper {
	
	protected static Session anfsession;
	protected static Transaction anftx;
	
	public QueryHelper() {
	}
	
	public static List<Anforderung> getFilteredAnforderungen(int anfID, String titel, String kunde, String verwandteAnf, String zugewiesen, String status, String schluesselbegriffe) {
		Session session = HibernateUtil.session;
			
		String stmt = "FROM Anforderung WHERE 1 = 1";
		if (anfID != 0)
			stmt += " and AnforderungID = :anfID";
		if (!titel.isEmpty())
			stmt += " and Titel = :titel";
		
		/*
		if (!kunde.isEmpty())
			stmt += " and kunde.Bezeichnung = :kunde";
		*/
		
		
		Query query = session.createQuery(stmt);
		if (anfID != 0)
			query.setParameter("anfID", anfID);
		if (!titel.isEmpty())
			query.setParameter("titel", titel);
		
		/*
		if (!kunde.isEmpty())
			query.setParameter("kunde", kunde);
		*/
		
		List<Anforderung> anforderungen = query.list();
		logData(anforderungen);
		
		return anforderungen;
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
	
	private static void logData(List liste)
	{
		for(int i = 0; i<liste.size(); i++)
		{
			System.out.println(liste.get(i).toString());
		}
	}

	/**
	 * eine Anforderung speichern
	 * @param anf
	 */
	public static void saveAnf(Anforderung anf) {
		Session session = HibernateUtil.session;
		Transaction tx = HibernateUtil.tx;
		
		System.out.println("saveAnf="+anf.getTitel());
		
		Serializable parentId = session.save(anf);
		
				
		//session.persist(anf);
		tx.commit();
		//session.close();
		System.out.println("parentId="+parentId);
		
		//session.flush();
		//session.refresh(anf);
		
		anfsession = session;
		//anftx = tx;
		//anfsession.flush();
	}
	
	/**
	 * Eine Anforderung mit einem Anhang speichern
	 * @param anf
	 * @param anh
	 */
	public static void saveAnf(Anforderung anf, Anhang anh) {
		Session session = HibernateUtil.session;
		Transaction tx = HibernateUtil.tx;

		Serializable parentId = session.save(anf);
		
		System.out.println("serializable="+parentId);

		
	    File anhfile = anh.getFile();
	    FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(anhfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Blob blob = Hibernate.getLobCreator(session)
                            .createBlob(inputStream, anhfile.length());
	    anh.setDatei(blob);
        
		
		anh.setAnforderung(anf);
		anf.getAnhaenge().add(anh);
		
		System.out.println("------ Anforderung="+anf.toString());
		for(Anhang a : anf.getAnhaenge()){
			System.out.println("------ Anhang="+a.toString());
		}
		
		session.save(anh);
		
		//Am Ende den Blob wieder freigeben
		try {
			blob.free();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Die Transaction commiten
		tx.commit();
	}
	
	

}
