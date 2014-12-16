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
@Table(name="Newspaper")
public class Newspaper {
    /**
     * Annotated properties/fields.
     */
    @Id
    @Column(name="NewspaperID", nullable=false)
    private Integer NewspaperID;

    @Column(name="Name", nullable=false)
    private String name;

    public Integer getNewspaperID() {
        return NewspaperID;
    }
    public void setNewspaperID(Integer newspaperID) {
        NewspaperID = newspaperID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
