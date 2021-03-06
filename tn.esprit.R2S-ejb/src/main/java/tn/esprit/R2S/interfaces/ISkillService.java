package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Skill;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
@Remote
public interface ISkillService {
    void create(Skill skill);

    Skill edit(Skill skill);

    void remove(Skill skill);

    Skill find(Object id);

    Skill findInitializeJob(Object id);

    List<Skill> findAll();
}
