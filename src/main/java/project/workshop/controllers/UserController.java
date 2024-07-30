package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.workshop.entities.User;
import project.workshop.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<List<User>> findAll(){
        List<User> listUser = userService.findAll();
        return ResponseEntity.ok().body(listUser);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<User> findById(@PathVariable Integer id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

}
