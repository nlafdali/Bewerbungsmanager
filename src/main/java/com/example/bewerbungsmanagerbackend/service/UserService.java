package com.example.bewerbungsmanagerbackend.service;

import com.example.bewerbungsmanagerbackend.model.User;
import com.example.bewerbungsmanagerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public User registerUser(User user){
        //prüfen,ob Email schon existiert
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("E-Mail-Adresse ist bereits registriert!!");
        }
        // Passwort verschlüsseln
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User loginUSer(String email, String password){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty()){
            throw new RuntimeException("Email-Adresse nicht gefunden");
        }
        User user = existingUser.get();

        System.out.println("DB-Passwort: " + user.getPassword());
        System.out.println("Eingegeben: " + password);
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Falsches Passwort!!");
        }
        return user;
    }

}
