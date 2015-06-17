package ca.owenpeterson;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ca.owenpeterson.dto.Address;
import ca.owenpeterson.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user = new UserDetails();
		
		//user.setUserId(1);
		user.setUserName("First User");
		user.setJoinedDate(new Date());
		user.setDescription("Description of first user goes here");
		Address addr = new Address();
		addr.setStreet("123 Fake Street");
		addr.setCity("Winnipeg");
		addr.setState("MB");
		addr.setPincode("R2M1A3");
		user.setAddress(addr);
		
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
		
		//put in finally after the catch.
		session.close();
		
		user = null;
		
		session = factory.openSession();
		session.beginTransaction();
		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("User name retrieved is: " + user.getUserName());
	}

}
