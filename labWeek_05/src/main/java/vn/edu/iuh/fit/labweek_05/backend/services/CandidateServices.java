package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.repositories.CandidateRepository;

import java.util.List;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize,String keyword, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if (keyword == null || keyword.isEmpty()) {
            return candidateRepository.findAll(pageable);
        }
        return candidateRepository.findByFullNameContainingIgnoreCase("%" + keyword + "%", pageable);
    }

    public List<Candidate> findAll() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id).get();
    }

    public boolean delete(Candidate candidate) {
        try {
            candidateRepository.delete(candidate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Candidate> findCandidateBySkill(Long skillId , SkillLevel skillLevel) {
        return candidateRepository.findByCandidateSkills_Skill_IdAndCandidateSkills_SkillLevel(skillId, skillLevel);
    }
    public List<Candidate> findCandidateBySkill(String skillName , SkillLevel skillLevel) {
        return candidateRepository.findByCandidateSkills_Skill_SkillNameAndCandidateSkills_SkillLevel(skillName, skillLevel);
    }
}
