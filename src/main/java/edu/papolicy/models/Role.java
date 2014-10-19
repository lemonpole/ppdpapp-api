package edu.papolicy.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RoleID", nullable=false)
	private int roleID;
	
	@Column(name="Name", nullable=false)
	private String name;
	
	/**
	* Getters.u
	*/
	public int getRoleID(){ return this.roleID; }
	public String getName(){ return this.name; }
	
	/**
	* Setters.
	*/
	public void setRoleID(int roleID){ this.roleID = roleID; }
	public void setName(String name){ this.name = name; }
}