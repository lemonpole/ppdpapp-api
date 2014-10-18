package edu.papolicy.models;


import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="FiledID", nullable=false)
    private int fileID;

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

    /**
     * Getters.
     */
    public int getBatchID(){ return this.batchID; }
    public Integer getFileID(){ return this.fileID; }
    public String getName(){ return this.name; }
    public Date getDateAdded(){ return this.dateAdded; }
    public String getCreator() {return this.creator; }
    public Date getDateDue() {return this.dateDue; }

    /**
     * Setters.
     */

    public void setBatchID(int batchID){ this.batchID = batchID;}
    public void setFileID(int fileID){this.fileID = fileID; }
    public void setName(String name){ this.name = name; }
    public void setDateAdded(Date dateAdded){ this.dateAdded = dateAdded; }
    public void setCreator(String creator) { this.creator = creator;}
    public void setDateDue(Date dateDue) {this.dateDue = dateDue; }
}




