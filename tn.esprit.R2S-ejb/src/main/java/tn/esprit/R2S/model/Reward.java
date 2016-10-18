/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import tn.esprit.R2S.util.enums.Progress;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class Reward implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Enumerated
    private Progress progress;

    @Basic
    private String points;

    @ManyToOne(targetEntity = Job.class)
    private Job job;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public String getPoints() {
        return this.points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}
