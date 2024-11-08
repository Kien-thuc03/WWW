package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return (List<Company>) companyRepository.findAll();
    }

    public Page<Company> findAll(int pageNo, int pageSize,String keyword, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if (keyword == null || keyword.isEmpty()) {
            return companyRepository.findAll(pageable);
        }
        return companyRepository.findByFullNameContainingIgnoreCase("%" + keyword + "%", pageable);
    }

    public Company save(Company comp) {
        return companyRepository.save(comp);
    }
    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }
}
