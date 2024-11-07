package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Long> {
  List<Job> findJobsByCompanyId(Long id) ;

  Page<Job> findAll(Pageable pageable);
}