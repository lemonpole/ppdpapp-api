package edu.papolicy.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * File entity.
 *
 * See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
 */
@Entity
@Table(name="Files")
public class File {
    /**
     * Annotated properties/fields.
     */
    @Id
    @Column(name="FileID", nullable=false)
    private Integer fileID;

    @Column(name="Name", nullable=false)
    private String name;

    @Column(name="FileURL", nullable=false)
    private String fileURL;

    @Column(name="DateAdded", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;

    @Column(name="Email", nullable=false)
    private String email;


    /*
    *getters and setters
     */

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
