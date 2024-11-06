package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;

public interface JobRepository extends CrudRepository<Job, Long> {
  }