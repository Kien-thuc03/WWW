package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillType;
import vn.edu.iuh.fit.labweek_05.backend.models.JobSkill;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobSkillRepository;

import java.util.List;

@Service
public class JobSkillServices {
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public List<JobSkill> findAll() {
        return (List<JobSkill>) jobSkillRepository.findAll();
    }

    public List<SkillType> getAllSkillTypes() {
        return List.of(SkillType.values());
    }

    public JobSkill save(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }


}
