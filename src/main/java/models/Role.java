package models;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;

/**
* Role entity.
* 
* See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
*/
@Entity
@Table(name="Roles")
public class Role {
	/**
	* Annotated properties/fields.
	*/
	@Id
	@Column(name="RoleID", nullable=false)
	private int roleID;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	@OneToMany(mappedBy="role")
	private Set<User> users;
	
	/**
	* Getters.
	*/
	public int getRoleID(){ return this.roleID; }
	public String getName(){ return this.name; }
	public Set<User> getUsers(){ return this.users; }
}