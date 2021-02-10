package com.example.web;

import com.example.models.entities.enums.RoleEnum;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("names",userService.findAllUsernames());
        return "role-add";
    }
    @PostMapping("/add")
    public String addConfirm(@RequestParam String username,
                             @RequestParam String role){

        userService.changeRole(username, RoleEnum.valueOf(role.toUpperCase()));

        return "redirect:/";
    }
}
