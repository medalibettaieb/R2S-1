package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.CandidateSkill;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("candidateSkill")
public class CandidateSkillFacade extends AbstractFacade<CandidateSkill> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateSkillFacade() {
        super(CandidateSkill.class);
    }

}
