package edu.papolicy.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
* Code entity.
*
* See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
*/
@Entity
@Table(name="Code")
public class Code {
    /**
    * Annotated properties/fields.
    */
    @Id
    @Column(name="Code", nullable=false)
    private Integer code;

    @Column(name="Description", nullable=false)
    private String description;


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
