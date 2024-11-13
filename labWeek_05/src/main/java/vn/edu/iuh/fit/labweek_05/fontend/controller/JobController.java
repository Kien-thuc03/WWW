package vn.edu.iuh.fit.labweek_05.fontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.labweek_05.backend.enums.SkillType;
import vn.edu.iuh.fit.labweek_05.backend.ids.JobSkillId;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.models.JobSkill;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobRepository;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.labweek_05.backend.services.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class JobController {
    @Autowired
    private JobServices jobServices;
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private JobSkillServices jobSkillServices;
    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/jobs")
    public String showJobListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size,
                                            @RequestParam("keyword") Optional<String> keyword) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String searchKeyword  = keyword.orElse("");
        Page<Job> jobPage = jobServices.findAll(
                currentPage - 1, pageSize,searchKeyword  , "id", "asc");


        model.addAttribute("jobPage", jobPage);
        model.addAttribute("search", searchKeyword);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/jobs-paging";
    }

    @GetMapping("/jobs/{id}/add-job")
    public String newJobForm(@PathVariable("id") Long companyID, Model model) {
        Company company = companyServices.findById(companyID);
        model.addAttribute("company", company);
        model.addAttribute("job", new Job());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("skillLevels", SkillLevel.values());
        return "/jobs/add-job";
    }

    @PostMapping("/jobs/{id}/add-job")
    public String createJob(@PathVariable("id") Long companyId,
                            @RequestParam("jobName") String jobName,
                            @RequestParam("jobDesc") String jobDesc,
                            @RequestParam("skills") List<Long> skills,
                            @RequestParam("skillLevels") List<String> skillLevels,
                            @RequestParam("more_infos") List<String> moreInfos) {
        // Khởi tạo và lưu thông tin công việc
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDesc(jobDesc);
        Company company = companyServices.findById(companyId);
        job.setCompany(company);

        Job savedJob = jobServices.save(job);

        // Lưu các kỹ năng cho công việc
        Set<JobSkill> jobSkills = new HashSet<>();
        for (int i = 0; i < skills.size(); i++) {
            JobSkill jobSkill = new JobSkill();
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(savedJob.getId());
            jobSkillId.setSkillId(skills.get(i));
            jobSkill.setId(jobSkillId);
            jobSkill.setJob(savedJob);
            jobSkill.setMoreInfos(moreInfos.get(i));
            jobSkill.setSkill(skillService.findById(skills.get(i)));
            jobSkill.setSkillLevel(SkillLevel.valueOf(skillLevels.get(i)));
            jobSkills.add(jobSkill);
            jobSkillServices.save(jobSkill);
        }

        savedJob.setJobSkills(jobSkills);
        jobServices.save(savedJob);

        return "redirect:/jobs";
    }




    @RequestMapping(value = "/jobs/{jobId}/{candidateId}/send-email", method = RequestMethod.GET)
    public String sendEmail(@PathVariable("jobId") Long jobId, @PathVariable("candidateId") Long candidateId, Model model) {
        Job job = jobServices.findById(jobId);
        Candidate candidate = candidateServices.findById(candidateId);
        model.addAttribute("job", job);
        model.addAttribute("candidate", candidate);
        return "jobs/send-email";
    }

    @RequestMapping(value = "/jobs/send-email", method = RequestMethod.POST)
    public String sendEmail(@RequestParam("jobId") Long jobId, @RequestParam("candidateId") Long candidateId, @RequestParam("subject") String subject, @RequestParam("emailBody") String emailBody) {
        Job job = jobServices.findById(jobId);
        Candidate candidate = candidateServices.findById(candidateId);
        jobServices.sendEmail(job, candidate, subject, emailBody);
        return "redirect:/jobs";
    }
}