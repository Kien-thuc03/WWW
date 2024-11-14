package edu.iuh.fit.labweek_06.fontend.controller;

import edu.iuh.fit.labweek_06.backend.models.Post;
import edu.iuh.fit.labweek_06.backend.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostController {
    @Autowired
    private PostServices postServices;

    @GetMapping("/posts")
    public String listPosts(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        Page<Post> postPage = postServices.findAll(currentPage - 1, pageSize, "createdAt", "desc");

        model.addAttribute("postPage", postPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);

        int totalPages = postPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/posts/post";
    }

    @RequestMapping("/posts/{id}")
    public String showPost(@PathVariable("id") Long id, Model model) {
        Post post = postServices.findById(id);
        model.addAttribute("post", post);
        return "/posts/post-detail";
    }
}
