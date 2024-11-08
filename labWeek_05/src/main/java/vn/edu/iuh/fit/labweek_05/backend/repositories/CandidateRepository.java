package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.models.Address;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, Long> , PagingAndSortingRepository<Candidate, Long> {
    @Query("""
            select c from Candidate c inner join c.candidateSkills candidateSkills
            where candidateSkills.skill.id = ?1 and candidateSkills.skillLevel = ?2""")
    List<Candidate> findByCandidateSkills_Skill_IdAndCandidateSkills_SkillLevel(Long id, SkillLevel skillLevel);

    @Query("""
            select c from Candidate c inner join c.candidateSkills candidateSkills
            where candidateSkills.skill.skillName = ?1 and candidateSkills.skillLevel = ?2""")
    List<Candidate> findByCandidateSkills_Skill_SkillNameAndCandidateSkills_SkillLevel(String skillName, SkillLevel skillLevel);

    @Query("""
         select c from Candidate c where
         upper(c.fullName) like upper(?1) or
         upper(c.phone) like upper(?1) or
         upper(c.email) like upper(?1)
         """)
    Page<Candidate> findByFullNameContainingIgnoreCase(String keyword, Pageable pageable);

}