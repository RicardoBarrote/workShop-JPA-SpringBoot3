package project.workshop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.workshop.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> testUser(User user) {
        User u = new User(1, "Ricardo", "ricardo@gmail.com", "159753", "81985745212");
        return ResponseEntity.ok().body(u);
    }

}
