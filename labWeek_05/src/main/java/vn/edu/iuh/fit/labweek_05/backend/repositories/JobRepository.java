package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.models.Skill;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
  List<Job> findJobsByCompanyId(Long id) ;

  Page<Job> findAll(Pageable pageable);

  @Query("""
          select j from Job j inner join j.jobSkills jobSkills
          where jobSkills.skillLevel = ?1 and jobSkills.skill.skillName = ?2""")
  List<Job> findByJobSkills_SkillLevelAndJobSkills_Skill_SkillName(SkillLevel skillLevel, String skillName);

  @Query("select j from Job j inner join j.jobSkills jobSkills where " +
          "upper(j.jobName) like upper(?1) " +
          "or upper(j.jobDesc) like upper(?1)" +
          "or upper(j.company.compName) like upper(?1)" +
          "or upper(jobSkills.skill.skillName) like upper(?1)")
  Page<Job> searchJobsRelativeByJobNameOrJobDescOrSkillName(String keyword, Pageable pageable);

}