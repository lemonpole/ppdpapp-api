package edu.papolicy.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* User entity.
* 
* See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
*/
@Entity
@Table(name="Users")
public class User {
	/**
	* Annotated properties/fields.
	*/
	@Id
	@Column(name="Email", nullable=false)
	private String email;
	
	@Column(name="RoleID", nullable=false)
	private int roleID;
	
	@Column(name="FirstName", nullable=false)
	private String firstName;
	
	@Column(name="LastName", nullable=false)
	private String lastName;
	
	@Column(name="IsActive", nullable=true)
	private boolean isActive;
	
	@Column(name="DateAdded", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dateAdded;
	
	/**
	* Constructor
	*/
	public User(){}
	
	/**
	* Getters.
	*/
	public String getEmail(){ return this.email; }
	public int getRoleID(){ return this.roleID; }
	public String getFirstName(){ return this.firstName; }
	public String getLastName(){ return this.lastName; }
	public boolean getIsActive(){ return this.isActive; }
	public Date getDateAdded(){ return this.dateAdded; }
	
	/**
	* Setters.
	*/
	public void setEmail(String email){ this.email = email; }
	public void setRoleID(int id){ this.roleID = id; }
	public void setFirstName(String firstName){ this.firstName = firstName; }
	public void setLastName(String lastName){ this.lastName = lastName; }
	public void setIsActive(boolean isActive){ this.isActive = isActive; }
	public void setDateAdded(Date dateAdded){ this.dateAdded = dateAdded; }
}