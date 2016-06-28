package dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	    public static final SessionFactory sessionFactory = buildSessionFactory();
	
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
	 

	}
