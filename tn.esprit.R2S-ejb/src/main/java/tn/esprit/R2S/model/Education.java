/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class Education implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String degree;

    @Basic
    private String institution;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return this.institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

}