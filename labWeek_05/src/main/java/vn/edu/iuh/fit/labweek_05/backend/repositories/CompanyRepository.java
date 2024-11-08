package vn.edu.iuh.fit.labweek_05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
   Page<Company> findAll(Pageable pageable) ;
   @Query("""
         select c from Company c where
         upper(c.compName) like upper(?1)
         or upper(c.email) like upper(?1)
         or upper(c.phone) like upper(?1 )
         or upper(c.webUrl) like upper(?1)
         """)
    Page<Company> findByFullNameContainingIgnoreCase(String keyword, Pageable pageable);
}