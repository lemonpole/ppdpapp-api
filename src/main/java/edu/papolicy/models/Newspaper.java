package edu.papolicy.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * Newspaper entity.
 *
 * See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
 */
@Entity
@Table(name="Newspapers")
public class Newspaper {
    /**
     * Annotated properties/fields.
     */
    @Id
    @Column(name="NewspaperID", nullable=false)
    private Integer NewspaperID;

    @Column(name="Name", nullable=false)
    private Integer name;

    public Integer getNewspaperID() {
        return NewspaperID;
    }
    public void setNewspaperID(Integer newspaperID) {
        NewspaperID = newspaperID;
    }

    public Integer getName() {
        return name;
    }
    public void setName(Integer name) {
        this.name = name;
    }


}
