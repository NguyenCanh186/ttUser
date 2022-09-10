package com.example.usermanager.controller;

import com.example.usermanager.model.User;
import com.example.usermanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("user", user.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("user", user.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.remove(user.getId());
        return "redirect:users";
    }

//    @GetMapping("/list")
//    public ResponseEntity<Iterable<User>> showAllUser(){
//        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<User> saveUser(@RequestBody User user){
//        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User user){
//        user.setId(id);
//        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable Long id){
//        Optional<User> userOptional = userService.findById(id);
//        if (!userOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        userService.remove(id);
//        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/findOne/{id}")
//    public ResponseEntity<User> findOne(@PathVariable Long id){
//        User user = userService.findById(id).get();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}
