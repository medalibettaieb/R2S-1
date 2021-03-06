/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author EvilKidss
 */
@Entity
@JsonIgnoreProperties({"candidateSkills", "jobs"})
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(targetEntity = CandidateSkill.class, mappedBy = "skill")
    private List<CandidateSkill> candidateSkills;

    @ManyToMany(targetEntity = Job.class, cascade = CascadeType.MERGE)
    private List<Job> jobs;

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

    public List<CandidateSkill> getCandidateSkills() {
        return this.candidateSkills;
    }

    public void setCandidateSkills(List<CandidateSkill> candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public List<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
