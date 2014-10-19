package edu.papolicy.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Document entity.
 *
 * See: http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html
 */
@Entity
@Table(name="Tables")
public class Document {
    /**
     * Annotated properties/fields.
     */
    @Id
    @Column(name="ID", nullable=false)
    private Integer id;

    @Column(name="TableName", nullable=false)
    private String tableName;

    @Column(name="TableTitle", nullable=false)
    private String tableTitle;

    @Column(name="MajorOnly", nullable=true)
    private Boolean majorOnly;

    @Column(name="MinYear", nullable=true)
    private Integer minYear;

    @Column(name="MaxYear", nullable=true)
    private Integer maxYear;

    @Column(name="Class", nullable=true)
    private String c1ass;

    @Column(name="TextColumn", nullable=true)
    private Integer textColumn;

    @Column(name="YearColumn", nullable=true)
    private Integer yearColumn;

    @Column(name="LinkColumn", nullable=true)
    private Integer linkColumn;

    @Column(name="DrillDownFields", nullable=true)
    private Integer drillDownFields;

    @Column(name="CodeColumn", nullable=true)
    private Integer codeColumn;

    @Column(name="Note", nullable=true)
    private Integer note;

    /*
    getters/setters
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Boolean getMajorOnly() {
        return majorOnly;
    }

    public void setMajorOnly(Boolean majorOnly) {
        this.majorOnly = majorOnly;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    public String getC1ass() {
        return c1ass;
    }

    public void setC1ass(String c1ass) {
        this.c1ass = c1ass;
    }

    public Integer getTextColumn() {
        return textColumn;
    }

    public void setTextColumn(Integer textColumn) {
        this.textColumn = textColumn;
    }

    public Integer getYearColumn() {
        return yearColumn;
    }

    public void setYearColumn(Integer yearColumn) {
        this.yearColumn = yearColumn;
    }

    public Integer getLinkColumn() {
        return linkColumn;
    }

    public void setLinkColumn(Integer linkColumn) {
        this.linkColumn = linkColumn;
    }

    public Integer getDrillDownFields() {
        return drillDownFields;
    }

    public void setDrillDownFields(Integer drillDownFields) {
        this.drillDownFields = drillDownFields;
    }

    public Integer getCodeColumn() {
        return codeColumn;
    }

    public void setCodeColumn(Integer codeColumn) {
        this.codeColumn = codeColumn;
    }
}
