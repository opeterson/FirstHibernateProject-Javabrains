package ca.owenpeterson.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity //(name="USER_DETAILS")
@Table(name="USER_DETAILS") //just changes the table name, not the entity name.
public class UserDetails {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //ask hibernate to generate this value. Auto generated number by default.
	//@Column(name="USER_ID")
	//@EmbeddedId //embedded object as primary key
	private int userId;
	
	//@Column(name="USER_NAME")
	//@Basic //used to make configuration changes without changing the datatype
	//@Transient // tell hibernate to ignore this field
	private String userName;
	
	@ElementCollection(fetch=FetchType.EAGER) //marks this object to be persisted.Loads all values from tables.  
	@JoinTable(name="USER_ADDRESS", joinColumns=@JoinColumn(name="USER_ID")) //change the name of the associated table as well as the primary key column name.
	//@GenericGenerator(name="hilo-gen", strategy="hilo") //common generator type
	//@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "hilo-gen", type = @Type(type="long")) //creates the address_id column as a primary key and uses a long value
	private Collection<Address> listOfAddresses = new ArrayList<Address>(); //has to be an interface that supports indexes
	
	@Temporal(TemporalType.DATE) //save only the date, not the time. Check ENUM for more options.
	private Date joinedDate;
	
	//@Lob //large object. 
	private String description;
	
	/**
	 * An example of embedding an object in a table.
	 */
//	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name="street", column=@Column(name="HOME_STREET_NAME")),
//		@AttributeOverride(name="city", column=@Column(name="HOME_CITY_NAME")),
//		@AttributeOverride(name="state", column=@Column(name="HOME_STATE_NAME")),
//		@AttributeOverride(name="pincode", column=@Column(name="HOME_PIN_CODE"))}) //prevents overlap of column names between objects
//	private Address officeAddress;
	
	/**
	 * Embedding an object in a table without renaming the columns.
	 */
	//@Embedded
	//private Address homeAddress;
	
	//annotations can also be placed on the getters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
		
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	
	
	
//	public Address getOfficeAddress() {
//		return officeAddress;
//	}
//	public void setOfficeAddress(Address officeAddress) {
//		this.officeAddress = officeAddress;
//	}
//	public Address getHomeAddress() {
//		return homeAddress;
//	}
//	public void setHomeAddress(Address homeAddress) {
//		this.homeAddress = homeAddress;
//	}

	
	
	
	
	
	
	
}
