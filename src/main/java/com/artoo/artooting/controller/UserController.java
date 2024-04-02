package com.artoo.artooting.controller;

import com.artoo.artooting.entity.User;
import com.artoo.artooting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list"; // Path to the Thymeleaf template listing users
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create"; // Path to the Thymeleaf template for creating a new user
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }
        model.addAttribute("user", userOptional.get());
        return "users/edit"; // Path to the Thymeleaf template for editing an existing user
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        // Check if user exists
        if (!userService.findById(id).isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }
        user.setId(id); // Ensure the ID is set for the user to update
        userService.save(user); // Save the updated user details
        redirectAttributes.addFlashAttribute("successMessage", "User has been updated successfully.");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "User has been deleted successfully.");
        return "redirect:/users";
    }
}

