package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
   Page<Company> findAll(Pageable pageable) ;
}