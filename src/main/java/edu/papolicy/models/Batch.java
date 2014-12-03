package edu.papolicy.models;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
* Batch entity.
*
* See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
*/
@Entity
@Table(name="Batches")
public class Batch {
    /**
    * Annotated properties/fields.
    */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="BatchID", nullable=false)
    private int batchID;

    @Column(name="FileID", nullable=true)
    private String fileID = null;

    @Column(name="TablesID", nullable=false)
    private int tablesID;

    @Column(name="Name", nullable=false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="DateAdded", nullable=false)
    private Date dateAdded;

    @Column(name="Creator", nullable=false)
    private String creator;

    @Temporal(TemporalType.DATE)
    @Column(name="DateDue", nullable=false)
    private Date dateDue;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "BatchUser", joinColumns = {@JoinColumn(name = "BatchID")}, inverseJoinColumns = {@JoinColumn(name = "Email")})
	private List<User> users;

	/**
    * Getters.
    */
    public int getBatchID(){ return this.batchID; }
    public String getFileID(){ return this.fileID; }
    public int getTablesID(){ return this.tablesID; }
    public String getName(){ return this.name; }
    public Date getDateAdded(){ return this.dateAdded; }
    public String getCreator(){ return this.creator; }
    public Date getDateDue(){ return this.dateDue; }
	public List<User> getUsers(){ return this.users; }

    /**
    * Setters.
    */
    public void setBatchID(int batchID){ this.batchID = batchID;}
    public void setFileID(String fileID){this.fileID = fileID; }
    public void setTablesID(int tablesID){this.tablesID = tablesID; }
    public void setName(String name){ this.name = name; }
    public void setDateAdded(Date dateAdded){ this.dateAdded = dateAdded; }
    public void setCreator(String creator){ this.creator = creator;}
    public void setDateDue(Date dateDue){ this.dateDue = dateDue; }
	public void setUsers(List<User> users){ this.users = users; }
}




