/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import tn.esprit.R2S.util.enums.Progress;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author EvilKidss
 */
@Entity
public class CandidateJob implements Serializable {

    @EmbeddedId
    private CandidateJobPK candidateJob;


    @Enumerated
    private Progress progress;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidateJob that = (CandidateJob) o;

        if (job != null ? !job.equals(that.job) : that.job != null) {
            return false;
        }
        return candidate != null ? candidate.equals(that.candidate) : that.candidate == null;

    }

    @Override
    public int hashCode() {
        return candidateJob.hashCode();
    }
}
