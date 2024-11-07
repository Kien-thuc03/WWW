package edu.iuh.fit.lad5.repositories;

import edu.iuh.fit.lad5.model.Candidate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CandidateRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = Logger.getLogger(CandidateRepository.class.getName());

    public CandidateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Candidate> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM candidates", Candidate.class);
    }

    public boolean add(Candidate candidate) {
        String sql = "INSERT INTO candidates (first_name, middle_name, last_name, dod, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql,
                candidate.getFirst_name().split(" ")[0],
                candidate.getMiddle_name().split(" ")[1],
                candidate.getLast_name().split(" ")[2],
                candidate.getDod(),
                candidate.getEmail(),
                candidate.getAddress(),
                candidate.getPhone()
        );
        if (result == 1) {
            logger.info("Candidate added successfully");
            return true;
        } else {
            logger.warning("Failed to add candidate");
            return false;
        }
    }
}
