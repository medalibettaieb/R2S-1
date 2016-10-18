/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import tn.esprit.R2S.util.enums.Progress;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class CandidateJob implements Serializable {

    @EmbeddedId
    private CandidateJobPK candidateJob;

    @Basic
    @Enumerated
    private Progress progress;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @MapsId("job")
    @ManyToOne(targetEntity = Job.class)
    private Job job;

    @MapsId("candidate")
    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    public CandidateJobPK getCandidateJob() {
        return this.candidateJob;
    }

    public void setCandidateJob(CandidateJobPK candidateJob) {
        this.candidateJob = candidateJob;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

}
