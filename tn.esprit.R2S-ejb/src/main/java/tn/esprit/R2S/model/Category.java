/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author EvilKidss
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(targetEntity = Question.class, mappedBy = "category")
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

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
