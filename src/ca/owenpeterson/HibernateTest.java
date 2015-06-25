package ca.owenpeterson;

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
		//user.setJoinedDate(new Date());
		//user.setDescription("Description of first user goes here");
		
		Address addr = new Address();
		addr.setStreet("123 Fake Street");
		addr.setCity("Winnipeg");
		addr.setState("MB");
		addr.setPincode("R2M1A3");
		//user.setHomeAddress(addr);
		
		Address addr2 = new Address();
		addr2.setStreet("Second Street");
		addr2.setCity("Second City");
		addr2.setState("Second State");
		addr2.setPincode("Second Pincode");
		//user.setOfficeAddress(addr2);
		
		user.getListOfAddresses().add(addr);
		user.getListOfAddresses().add(addr2);
		
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
		//session.beginTransaction();
		user = (UserDetails) session.get(UserDetails.class, 1);
		session.close(); //prevent the proxy object form getting the addresses. Should result in LazyInitializationException
		
		System.out.println(user.getListOfAddresses().size()); //hibernate only now populates the user with it's list of addresses. Lazy Initialization.
		//System.out.println("User name retrieved is: " + user.getUserName());
		//session.getTransaction().commit();
		//session.close();
	}

}
