/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.hrsmart.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class CandidateQuizModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date passingDate;

    @OneToOne(targetEntity = CandidateAnswer.class, mappedBy = "candidateQuizModel")
    private CandidateAnswer candidateAnswer;

    @OneToOne(targetEntity = QuizModel.class)
    private QuizModel quizModel;

    @OneToOne(targetEntity = Job.class)
    private Job job;

    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPassingDate() {
        return this.passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }

    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }

    public QuizModel getQuizModel() {
        return this.quizModel;
    }

    public void setQuizModel(QuizModel quizModel) {
        this.quizModel = quizModel;
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
