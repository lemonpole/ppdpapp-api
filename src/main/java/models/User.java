package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
	
	@ManyToOne
	@JoinColumn(name="RoleID")
	private Role role;
	
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
	public Role getRole(){ return this.role; }
	public String getFirstName(){ return this.firstName; }
	public String getLastName(){ return this.lastName; }
	public boolean getIsActive(){ return this.isActive; }
	public Date getDateAdded(){ return this.dateAdded; }
}