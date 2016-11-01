/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author EvilKidss
 */
@Entity
public class CandidateAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = CandidateQuizModel.class)
    private CandidateQuizModel candidateQuizModel;

    @OneToMany(targetEntity = Question.class, mappedBy = "candidateAnswer")
    private List<Question> questions;

    @OneToMany(targetEntity = Answer.class, mappedBy = "candidateAnswer", fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CandidateQuizModel getCandidateQuizModel() {
        return this.candidateQuizModel;
    }

    public void setCandidateQuizModel(CandidateQuizModel candidateQuizModel) {
        this.candidateQuizModel = candidateQuizModel;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<Answer>(this.answers);
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = new HashSet<Answer>(answers);
    }

}
