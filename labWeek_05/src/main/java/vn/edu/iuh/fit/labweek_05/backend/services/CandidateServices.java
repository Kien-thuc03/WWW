package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.repositories.CandidateRepository;

import java.util.List;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    public List<Candidate> findAll() {
        return (List<Candidate>) candidateRepository.findAll();
    }
}
