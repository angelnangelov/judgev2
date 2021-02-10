package com.example.web;

import com.example.models.binding.UserLoginBindingModel;
import com.example.models.binding.UserRegisterBindingModel;
import com.example.models.service.UserServiceModel;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserRegisterBindingModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes
                       ){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        UserServiceModel user =
                userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());
        if(user ==null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound",true);

            return "redirect:login";

        }
        //Save user in session
        //  httpSession.setAttribute("user",user);
        userService.login(user);

        return "redirect:/";

    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(
            @Valid
            @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword()
                        .equals(userRegisterBindingModel.getConfirmPassword())) {

            //запазва верните полета
            redirectAttributes.addFlashAttribute
                    ("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        userService.registerUser(userServiceModel);

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}
