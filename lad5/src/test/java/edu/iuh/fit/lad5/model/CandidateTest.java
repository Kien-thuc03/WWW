package edu.iuh.fit.lad5.model;

import edu.iuh.fit.lad5.services.CandidateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CandidateTest {
    private CandidateService candidateService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCandidate() {
        List<Candidate> candidates = candidateService.getAllCandidates();

        assertNotNull(candidates);
        assertEquals(5, candidates.size());
    }
}