package com.example.bewerbungsmanagerbackend.controller;

import com.example.bewerbungsmanagerbackend.model.User;
import com.example.bewerbungsmanagerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") //Für Frontend (z.B. localhost: 8080)
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            User saveUser = userService.registerUser(user);
            return ResponseEntity.ok(saveUser);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){

        try{
            User loggedInUser = userService.loginUSer(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(loggedInUser);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
