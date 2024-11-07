package edu.iuh.fit.lad5.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CandidateMapper implements RowMapper<Candidate> {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getString("id"));
        candidate.setFirst_name(rs.getString("first_name"));
        candidate.setMiddle_name(rs.getString("middle_name"));
        candidate.setLast_name(rs.getString("last_name"));
        candidate.setDod(rs.getDate("dod"));
        candidate.setEmail(rs.getString("email"));
        candidate.setPhone(rs.getString("phone"));
        candidate.setAddress(rs.getString("address"));
        return candidate;
    }

    public Candidate getById(int id) {
        return template.queryForObject("select * from candidate where id = ?",
                new CandidateMapper(), id);
    }

    public void add(Candidate candidate) {
        template.update("insert into candidate (first_name, middle_name, last_name, dod, email, phone, address) values (?, ?, ?, ?, ?, ?, ?)",
                candidate.getFirst_name(), candidate.getMiddle_name(), candidate.getLast_name(), candidate.getDod(), candidate.getEmail(), candidate.getPhone(), candidate.getAddress());
    }

    public void update(Candidate candidate) {
        template.update("update candidate set first_name = ?, middle_name = ?, last_name = ?, dod = ?, email = ?, phone = ?, address = ? where id = ?",
                candidate.getFirst_name(), candidate.getMiddle_name(), candidate.getLast_name(), candidate.getDod(), candidate.getEmail(), candidate.getPhone(), candidate.getAddress(), candidate.getId());
    }

    public void delete(int id) {
        template.update("delete from candidate where id = ?", id);
    }

    public List<Candidate> findAll() {
        return template.query("select * from candidate", new CandidateMapper());
    }
}