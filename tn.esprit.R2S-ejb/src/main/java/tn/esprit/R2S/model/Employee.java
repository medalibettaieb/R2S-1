/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class Employee extends Users implements Serializable {

    @Basic
    private Integer credibility;

    @OneToMany(targetEntity = Candidate.class, mappedBy = "referee")
    private List<Candidate> referredCandidates;

    public Integer getCredibility() {
        return this.credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public List<Candidate> getReferredCandidates() {
        return this.referredCandidates;
    }

    public void setReferredCandidates(List<Candidate> referredCandidates) {
        this.referredCandidates = referredCandidates;
    }

}
