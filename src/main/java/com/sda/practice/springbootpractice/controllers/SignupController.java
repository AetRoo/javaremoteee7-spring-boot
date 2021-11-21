package com.sda.practice.springbootpractice.controllers;

import com.sda.practice.springbootpractice.models.Users;
import com.sda.practice.springbootpractice.services.AuthorityService;
import com.sda.practice.springbootpractice.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller to handle signup related requests
 *
 * @author Vinod John
 */
@Controller
@RequestMapping("/signup")
public class SignupController {
  @Autowired
  private UsersService usersService;

  @Autowired
  private AuthorityService authorityService;

  @GetMapping
  public String showSignupPage(@ModelAttribute("users") Users users, @ModelAttribute("message") String message,
                               @ModelAttribute("messageType") String messageType, Model model) {
    model.addAttribute("authorities", authorityService.getAllAuthorities());
    return "auth/signup";
  }

  @PostMapping
  public String postSignup(Users users, RedirectAttributes redirectAttributes) {
    if (!usersService.findUserByUsername(users.getUsername()).isPresent()) {
      usersService.createUser(users);
      redirectAttributes.addFlashAttribute("message", "Signup successful!");
      redirectAttributes.addFlashAttribute("messageType", "success");
      return "redirect:/";
    }
    else {
      redirectAttributes.addFlashAttribute("message", "User already exists!");
      redirectAttributes.addFlashAttribute("messageType", "error");
      return "redirect:/signup";
    }
  }
}
