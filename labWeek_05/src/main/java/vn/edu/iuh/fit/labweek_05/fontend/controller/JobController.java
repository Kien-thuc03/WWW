package vn.edu.iuh.fit.labweek_05.fontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.labweek_05.backend.models.Candidate;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.repositories.JobRepository;
import vn.edu.iuh.fit.labweek_05.backend.services.CandidateServices;
import vn.edu.iuh.fit.labweek_05.backend.services.CompanyServices;
import vn.edu.iuh.fit.labweek_05.backend.services.JobServices;

import java.util.List;
import java.util.Optional;
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

    @RequestMapping(value = "/jobs")
    public String showJobListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Job> jobPage= jobServices.findAll(
                currentPage - 1,pageSize,"id","asc");


        model.addAttribute("jobPage", jobPage);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/jobs-paging";
    }

    @GetMapping("/jobs/add-job")
    public String newJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/add-job";
    }

    @PostMapping("/jobs")
    public String createJob(@ModelAttribute Job job) {
        jobServices.save(job);
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


}