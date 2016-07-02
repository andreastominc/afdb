package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	public QueryHelper() {
	}
	
	public static List<Anforderung> getFilteredAnforderungen(int anfID, String titel, String kunde, String verwandteAnf, String zugewiesen, String status, String schluesselbegriffe) {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		String stmt = "SELECT anf FROM Anforderung anf"
				+ " left outer join anf.kunde k"
				+ " left outer join anf.status sta"
				+ " left outer join anf.zugewiesenAn z"
				+ " WHERE 1 = 1";

		if (anfID != 0)
			stmt += " and anf.anfId = :anfID";
		if (!titel.isEmpty())
			stmt += " and anf.titel = :titel";
		if (!kunde.isEmpty())
			stmt += " and k.bezeichnung = :kunde";
		if (!verwandteAnf.isEmpty()){
			String[] verwAnf = verwandteAnf.split(",");
			stmt += " and (";
			for(int i=0; i < verwAnf.length; i++){
				stmt += " instr(verwAnforderungen,'" + verwAnf[i] + "') > 0 or";
			}
			stmt += " 1=2)";
		}
		if (!zugewiesen.isEmpty())
			stmt += " and z.benutzername = :zugewiesen";
		if (!status.isEmpty())
			stmt += " and sta.bezeichnung = :status";
		if (!schluesselbegriffe.isEmpty()){
			String[] begriffe = schluesselbegriffe.split(",");
			stmt += " and (";
			for(int i=0; i < begriffe.length; i++){
				stmt += " instr(schluesselBegriffe,'" + begriffe[i] + "') > 0 or";
			}
			stmt += " 1=2)";
		}
		
		Query query = session.createQuery(stmt);
		if (anfID != 0)
			query.setParameter("anfID", anfID);
		if (!titel.isEmpty())
			query.setParameter("titel", titel);
		if (!kunde.isEmpty())
			query.setParameter("kunde", kunde);
	    /*
		if (!verwandteAnf.isEmpty())
			query.setParameter("verwandteAnf", verwandteAnf);
		*/
		if (!zugewiesen.isEmpty())
			query.setParameter("zugewiesen", zugewiesen);
		if (!status.isEmpty())
			query.setParameter("status", status);
		/*
		if (!schluesselbegriffe.isEmpty())
			query.setParameter("schluesselbegriffe", schluesselbegriffe);
		*/
				
		List<Anforderung> anforderungen = query.list();
		logData(anforderungen);
		return anforderungen;
	}
	
	public static List<Status> getAllStatus() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<Status> status = session.createQuery(" from Status").list();
		logData(status);
		
		return status;
	}
	
	public static List<Prioritaet> getAllPrioritaet() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<Prioritaet> prioritaet = session.createQuery(" from Prioritaet").list();
		logData(prioritaet);
		
		return prioritaet;
	}
	
	public static List<Modul> getAllModul() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<Modul> modul = session.createQuery(" from Modul").list();
		logData(modul);
		
		return modul;
	}
	
	public static List<AnforderungsArt> getAllAnforderungsArt() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<AnforderungsArt> anforderungsArt = session.createQuery(" from AnforderungsArt").list();
		logData(anforderungsArt);
		
		return anforderungsArt;
	}
	
	public static List<Anforderung> getAllAnforderungen() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<Anforderung> anforderungen = session.createQuery(" from Anforderung").list();
		logData(anforderungen);
		
		return anforderungen;
	}
	
	public static List<Version> getAllVersion() {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		List<Version> version = session.createQuery(" from Version").list();
		logData(version);
		
		return version;
	}
	
	public static List<Benutzer> getBenutzerMitSchreibRecht(boolean schreibRecht) {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		String stmt = "select b from Benutzer b inner join b.art ba"+
					  " where ba.schreibRecht = :schreibrecht";
		List<Benutzer> benutzer = session.createQuery(stmt).setBoolean("schreibrecht", schreibRecht).list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Kunde> getAllKunden() {
		Session session = HibernateUtil.sessionFactory.openSession();
	
		List<Kunde> benutzer = session.createQuery(" from Kunde").list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Benutzer> getAnsprechpersonVonKunde(Kunde kd) {
		Session session = HibernateUtil.sessionFactory.openSession();
	
		String stmt = "select distinct ap from Kunde kd inner join kd.kontaktPerson ap "+
				  " where kd.kundenId= :id";
		List<Benutzer> benutzer = session.createQuery(stmt).setInteger("id", kd.getKundenId()).list();
		
		logData(benutzer);
		
		return benutzer;
	}
	
	public static List<Benutzer> getAllAnsprechpersonen() {
		Session session = HibernateUtil.sessionFactory.openSession();
	
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
	
	public static boolean checkUser(String userToCheck)
	{
			Session session = HibernateUtil.sessionFactory.openSession();
			String stmt = "from Benutzer b where b.benutzername = :user";			
		
			List<Benutzer> benutzer = session.createQuery(stmt).setString("user",userToCheck).list();
			
			if(benutzer.isEmpty())
			{
				return false;
			}
								
				return true;
	}
	
	
	
	public static boolean checkPasswort(String passWort)
	{
		
		Session session = HibernateUtil.sessionFactory.openSession();
		String stmt = "from Benutzer b where b.passwort = :pw";			
	
		List<Benutzer> benutzer = session.createQuery(stmt).setString("pw",passWort).list();
		
//		for (Benutzer benutzer2 : benutzer) {
//			System.out.println("pw: " + benutzer2.getPasswort());
//		}
		
		if(benutzer.isEmpty())
		{
			return false;
		}
				
		return true;
	}
	
	
	/**
	 * Aus der DB den Benutzer ermitteln, der den Usernamen mit dem Passwort hat.
	 * Als Rueckgabewert erhaelt man den ermittelten Benutzer.
	 * @param benutzername
	 * @param passwort
	 * @return
	 */
	public static Benutzer authenticateUser(String benutzername, String passwort){
		Session session = HibernateUtil.sessionFactory.openSession();
		String stmt = "from Benutzer b where b.benutzername = :user and b.passwort = :pw";	
		Query query = session.createQuery(stmt);
		if (!benutzername.isEmpty()){
			query.setParameter("user", benutzername);
		}
		if (!passwort.isEmpty()){
			query.setParameter("pw", passwort);
		}
		
		List<Benutzer> ben = query.list();
		if(ben.isEmpty()){
			return null;
		}else{
			Benutzer benutzer = (Benutzer) query.list().get(0);
			return benutzer;
		}
	}
	

	/**
	 * eine Anforderung speichern
	 * @param anf
	 */
	public static void saveAnf(Anforderung anf) {

		Session session = HibernateUtil.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();

		session.saveOrUpdate(anf); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		tx.commit();
		session.close();
	}
	
	/**
	 * Eine Anforderung mit einem Anhang speichern
	 * @param anf
	 * @param anh
	 */
	public static void saveAnf(Anforderung anf, Anhang anh) {
		Session session = HibernateUtil.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		// Serializable parentId = session.save(anf);

		session.saveOrUpdate(anf); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		
		//System.out.println("serializable="+parentId);

		
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
		
		session.saveOrUpdate(anh); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		
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
	
	// ------------------------------------------------

	/**
	 * eine Anforderung updaten
	 * @param anf
	 */
	public static void updateAnf(Anforderung anf) {
		System.out.println("---save Anforderung ohne Anhang.");
		Session session = HibernateUtil.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		// die Anforderung muss aus der Hibernate Session geladen werden:
		Anforderung a1 = (Anforderung) session.get(Anforderung.class, anf.getAnfId());
		
		//Anforderung a = (Anforderung) session.merge(anf);
		//System.out.println("---save Anforderung ohne Anhang="+a.getAnfId());
		
		System.out.println("---vorher:session.get="+a1.toString());
		
		// dann die Daten der geladenen Anforderung mit den Daten der upgedateten Anforderung setzen:
		a1.setAnfArt(anf.getAnfArt());
		a1.setAngelegtVon(anf.getAngelegtVon());
		a1.setAnsprechPerson(anf.getAnsprechPerson());
		a1.setAufwandGeschaetzt(anf.getAufwandGeschaetzt());
		a1.setBeschreibung(anf.getBeschreibung());
		a1.setErfassungsDatum(anf.getErfassungsDatum());
		a1.setFertiggeplant(anf.getFertiggeplant());
		a1.setFertigIst(anf.getFertigIst());
		a1.setHdNummer(anf.getHdNummer());
		a1.setKunde(anf.getKunde());
		a1.setModul(anf.getModul());
		a1.setPrio(anf.getPrio());
		a1.setModul(anf.getModul());
		a1.setSchluesselBegriffe(anf.getSchluesselBegriffe());
		a1.setStatus(anf.getStatus());
		a1.setTitel(anf.getTitel());
		a1.setVersion(anf.getVersion());
		a1.setVerwAnforderungen(anf.getVerwAnforderungen());
		a1.setZugewiesenAn(anf.getZugewiesenAn());
		
		// to do ... anhaenge ... geht noch nicht.
		//a1.getAnhaenge().clear();
		//a1.setAnhaenge(anf.getAnhaenge());
		
		System.out.println("---nachher:session.get="+a1.toString());

		// dann die geladene und upgedatete Anforderung speichern:
		session.update(a1); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		tx.commit();
		session.close();
	}
	
	/**
	 * Eine Anforderung mit Anhang updaten
	 * @param anf
	 * @param anh
	 */
	public static void updateAnf(Anforderung anf, Anhang anh) {
		System.out.println("---save Anforderung mit Anhang.");
		Session session = HibernateUtil.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.clear();
		
		// Serializable parentId = session.save(anf);
		Anforderung a1 = (Anforderung) session.merge(anf);
		System.out.println("---save Anforderung mit Anhang="+a1.getAnfId());
		//session.saveOrUpdate(anf); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		
		//System.out.println("serializable="+parentId);

		
	    File anhfile = anh.getFile();
	    System.out.println("anhfile="+anh.getFile().getName());
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
	    

		System.out.println("im queyhelper: anhang: "+anh.getAnhangId()+", "+anh.getAnforderung().getAnfId());
		session.saveOrUpdate(anh); // saveOrUpdate anstatt save, damit auch verwendbar fuers Bearbeiten
		
		//Am Ende den Blob wieder freigeben
		try {
			blob.free();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Die Transaction commiten
		tx.commit();
	}
	
	
	
	

}
