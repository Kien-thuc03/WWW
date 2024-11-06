package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
  }