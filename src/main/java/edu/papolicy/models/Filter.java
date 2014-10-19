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
@Table(name="Filters")
public class Filter {

    /**
     * Annotated properties/fields.
     */
    @Id
    @Column(name="ID", nullable=false)
    private Integer id;

    @Column(name="TableID", nullable=false)
    private Integer tableID;

    @Column(name="ColumnName", nullable=false)
    private String columnName;

    @Column(name="Description", nullable=true)
    private String description;

    @Column(name="FilterClass", nullable=false)
    private String filterClass;

    @Column(name="TableReference", nullable=false)
    private String tableReference;

    @Column(name="AdditionalParam", nullable=false)
    private String additionalParam;



    /**
     * getters/setters
     */

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getTableReference() {
        return tableReference;
    }

    public void setTableReference(String tableReference) {
        this.tableReference = tableReference;
    }

    public String getAdditionalParam() {
        return additionalParam;
    }

    public void setAdditionalParam(String additionalParam) {
        this.additionalParam = additionalParam;
    }
}
