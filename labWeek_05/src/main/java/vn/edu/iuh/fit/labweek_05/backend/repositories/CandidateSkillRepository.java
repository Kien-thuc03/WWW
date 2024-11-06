package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.labweek_05.backend.models.CandidateSkill;

public interface CandidateSkillRepository extends CrudRepository<CandidateSkill, CandidateSkillId> {
  }