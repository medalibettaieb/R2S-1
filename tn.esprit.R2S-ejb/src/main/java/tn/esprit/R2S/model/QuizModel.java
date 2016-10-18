/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class QuizModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String name;

    @Basic
    private Integer duration;

    @Basic
    private Integer questionsNumber;

    @Basic
    private Integer answersNumber;

    @Basic
    private Boolean penalty;

    @OneToMany(targetEntity = Job.class, mappedBy = "quizModel")
    private List<Job> jobs;

    @ManyToMany(targetEntity = Question.class, mappedBy = "quizModels")
    private List<Question> questions;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuestionsNumber() {
        return this.questionsNumber;
    }

    public void setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public Integer getAnswersNumber() {
        return this.answersNumber;
    }

    public void setAnswersNumber(Integer answersNumber) {
        this.answersNumber = answersNumber;
    }

    public Boolean isPenalty() {
        return this.penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public List<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
