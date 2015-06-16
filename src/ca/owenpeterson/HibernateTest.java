package ca.owenpeterson;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ca.owenpeterson.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user = new UserDetails();
		
		user.setUserId(3);
		user.setUserName("Third User");
		
		//required for Hibernate 4 (differs from tutorial);
		//tutorial mentions that a try catch should be used and do a rollback in the catch.
		//will likely be covered later, as this is a starter.
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory((builder.build()));
		
		Session session = factory.openSession();
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

}
