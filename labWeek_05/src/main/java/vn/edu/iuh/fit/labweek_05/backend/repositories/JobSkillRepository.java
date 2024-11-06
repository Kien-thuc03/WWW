package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.ids.JobSkillId;
import vn.edu.iuh.fit.labweek_05.backend.models.JobSkill;

public interface JobSkillRepository extends CrudRepository<JobSkill, JobSkillId> {
  }