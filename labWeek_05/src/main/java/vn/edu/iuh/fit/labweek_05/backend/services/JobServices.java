package vn.edu.iuh.fit.labweek_05.backend.services;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.models.CandidateSkill;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.models.Skill;
import vn.edu.iuh.fit.labweek_05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobRepository;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class JobServices {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Job> findAllJob() {
        return (List<Job>) jobRepository.findAll();
    }

    public Page<Job> findAll(int pageNo, int pageSize,String keyword, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if (keyword == null || keyword.isEmpty()) {
            return jobRepository.findAll(pageable);
        }
        return jobRepository.searchJobsRelativeByJobNameOrJobDescOrSkillName("%" + keyword + "%", pageable);
    }

    public Page<Candidate> findAlal(int pageNo, int pageSize,String keyword, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if (keyword == null || keyword.isEmpty()) {
            return candidateRepository.findAll(pageable);
        }
        return candidateRepository.findByFullNameContainingIgnoreCase("%" + keyword + "%", pageable);
    }

    public Job save(Job Job) {
        return jobRepository.save(Job);
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).get();
    }

    public List<Job> findJobByCompany(Long id) {
        return jobRepository.findJobsByCompanyId(id);
    }

    private JavaMailSender mailSender;
    private final Logger logger = Logger.getLogger(JobRepository.class.getName());

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Job job, Candidate candidate, String subject, String emailBody) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(candidate.getEmail())
            );
            mimeMessage.setSubject("Job Invitation");
            mimeMessage.setFrom(new InternetAddress(job.getCompany().getEmail()));

//            String emailBody = """
//               Dear %s,
//
//               We are pleased to invite you to apply for the position of %s at our company.
//
//               Job Description:
//               %s
//
//               Please let us know if you are interested.
//
//               Best regards,
//               %s
//               """.formatted(
//                    candidate.getFullName(),
//                    job.getJobName(),
//                    job.getJobDesc(),
//                    job.getCompany().getCompName()
//            );

            mimeMessage.setText(emailBody);

        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            logger.log(Level.SEVERE, "An error occurred while sending the email: " + ex.getMessage());
            throw new RuntimeException("An error occurred while sending the email: " + ex.getMessage());
        }
    }

    public List<Job> suggestJobsForCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));

        return candidate.getCandidateSkills().stream()
                .map(candidateSkill -> jobRepository.findByJobSkills_SkillLevelAndJobSkills_Skill_SkillName(
                        candidateSkill.getSkillLevel(),
                        candidateSkill.getSkill().getSkillName()
                ))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

}
