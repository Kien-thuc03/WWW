package vn.edu.iuh.fit.labweek_05.fontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.labweek_05.backend.models.Company;
import vn.edu.iuh.fit.labweek_05.backend.models.Job;
import vn.edu.iuh.fit.labweek_05.backend.services.CompanyServices;
import vn.edu.iuh.fit.labweek_05.backend.services.JobServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CompanyController {
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private JobServices jobServices;
    @RequestMapping(value = "/companies")
    public String showCompanyListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size,
                                        @RequestParam("keyword") Optional<String> keyword) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String searchKeyword  = keyword.orElse("");
        Page<Company> companyPage= companyServices.findAll(
                currentPage - 1,pageSize,searchKeyword,"id","asc");


        model.addAttribute("companyPage", companyPage);
        model.addAttribute("search", searchKeyword);

        int totalPages = companyPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "companies/companies-paging";
    }

    @RequestMapping(value = "/companies/{id}")
    public String showCompanyDetail(Model model, @PathVariable Long id) {
        Company company = companyServices.findById(id);
        model.addAttribute("company", company);
        List< Job > jobs = jobServices.findJobByCompany(id);
        model.addAttribute("jobs", jobs);
        return "companies/company-detail";
    }

    @RequestMapping(value = "/companies/{id}/jobs")
    public String showCompanyJobList(Model model, @PathVariable Long id) {
        Company company = companyServices.findById(id);
        model.addAttribute("company", company);
        List< Job > jobs = jobServices.findJobByCompany(id);
        model.addAttribute("jobs", jobs);
        return "companies/company-detail";
    }
}
