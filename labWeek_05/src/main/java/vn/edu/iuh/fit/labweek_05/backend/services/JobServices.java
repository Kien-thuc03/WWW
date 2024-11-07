package vn.edu.iuh.fit.labweek_05.backend.services;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JobServices {
    @Autowired
    private JobRepository JobRepository;

    public List<Job> findAllJob() {
        return (List<Job>) JobRepository.findAll();
    }

    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return JobRepository.findAll(pageable);
    }

    public Job save(Job Job) {
        return JobRepository.save(Job);
    }

    public Job findById(Long id) {
        return JobRepository.findById(id).get();
    }

    public List<Job> findJobByCompany(Long id) {
        return JobRepository.findJobsByCompanyId(id);
    }

//    private JavaMailSender mailSender;
//    private final Logger logger = Logger.getLogger(JobRepository.class.getName());
//
//    @Autowired
//    public void setMailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendEmail(Job job, Candidate candidateDto) {
//        MimeMessagePreparator preparator = mimeMessage -> {
//            mimeMessage.setRecipient(Message.RecipientType.TO,
//                    new InternetAddress(candidateDto.email())
//            );
//            mimeMessage.setSubject("Job Invitation");
//            mimeMessage.setFrom(new InternetAddress(job.companyEmail()));
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
//                    candidateDto.fullName(),
//                    job.jobName(),
//                    job.jobDesc(),
//                    job.companyName()
//            );
//
//            mimeMessage.setText(emailBody);
//
//        };
//        try {
//            this.mailSender.send(preparator);
//        } catch (MailException ex) {
//            logger.log(Level.SEVERE, "An error occurred while sending the email: " + ex.getMessage());
//            throw new RuntimeException("An error occurred while sending the email: " + ex.getMessage());
//        }
//    }
}
