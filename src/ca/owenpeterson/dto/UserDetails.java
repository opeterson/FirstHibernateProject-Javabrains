package ca.owenpeterson.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Temporal(TemporalType.DATE) //save only the date, not the time. Check ENUM for more options.
	private Date joinedDate;
	
	//@Lob //large object. 
	private String description;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="HOME_STREET_NAME")),
		@AttributeOverride(name="city", column=@Column(name="HOME_CITY_NAME")),
		@AttributeOverride(name="state", column=@Column(name="HOME_STATE_NAME")),
		@AttributeOverride(name="pincode", column=@Column(name="HOME_PIN_CODE"))}) //prevents overlap of column names between objects
	private Address officeAddress;
	
	@Embedded
	private Address homeAddress;
	
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
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	
	
	
	
	
	
	
}
