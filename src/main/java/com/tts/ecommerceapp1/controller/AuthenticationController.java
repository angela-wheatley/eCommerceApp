package com.tts.ecommerceapp1.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.ecommerceapp1.model.User;
import com.tts.ecommerceapp1.service.UserService;

@Controller
class AuthenticationController
{
    @Autowired
    private UserService userService;
    
    @GetMapping("/signin")
    public String login(User user)
    {
        return "signin";
    }
    
    @PostMapping("/signin")
    public String signup(@Valid User user, BindingResult bindingResult, @RequestParam String submit, HttpServletRequest request) throws ServletException
    {
        String password = user.getPassword();
        if(submit.equals("up"))
        {
            if(userService.testPassword(password) == false)
            {
                bindingResult.rejectValue("password", "error.user", 
                        "Password must be longer than 8 characters, contain at least 1 capital letter and 1 lowercase letter, "
                        + "1 number and 1 special character.");
                
            }
            if(bindingResult.hasErrors())
            {
                return "signin";
            }
            if(userService.findByUsername(user.getUsername()) == null)
            {
                userService.saveNew(user);
            }
            else
            {
                bindingResult.rejectValue("username", "error.user", "Username is already taken.");
                return "signin";
            }
        }
        request.login(user.getUsername(), password);
        return "redirect:/";
    }
    
}
