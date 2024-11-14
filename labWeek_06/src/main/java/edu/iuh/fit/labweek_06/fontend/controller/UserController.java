package edu.iuh.fit.labweek_06.fontend.controller;

import edu.iuh.fit.labweek_06.backend.services.UserServives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserServives userServives;

    @RequestMapping("/user")
    public String listUser(Model model) {
        model.addAttribute("users", userServives.findAll());
        return "user/user";
    }
}
