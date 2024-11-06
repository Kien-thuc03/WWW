package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> , PagingAndSortingRepository<Candidate, Long> {
}