package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.models.Skill;
import vn.edu.iuh.fit.labweek_05.backend.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return (List<Skill>) skillRepository.findAll();
    }

    public Skill findById(Long aLong) {
        return skillRepository.findById(aLong).get();
    }
}
