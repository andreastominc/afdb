package dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	    private static final SessionFactory sessionFactory = buildSessionFactory();
	    public static final Session session = createSession();
	    public static final Transaction tx = createTransaction();
	 
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from connections.cfg.xml
	            return new Configuration()
	            		.configure("connections.cfg.xml")
	                    .buildSessionFactory();
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	    
	    private static Session createSession()
	    {
	    	return sessionFactory.openSession();
	    }
	    
	    private static Transaction createTransaction()
	    {
	    	return session.beginTransaction();
	    }
	}
