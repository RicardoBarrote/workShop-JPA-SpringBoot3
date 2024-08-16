package project.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.workshop.entities.User;
import project.workshop.requestPayLoad.UserRequestPayLoad;
import project.workshop.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUser() {
        List<User> listUser = userService.findAll();
        return ResponseEntity.ok().body(listUser);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createdUser(@RequestBody UserRequestPayLoad payLoad) {
        User user = userService.createdUser(payLoad);

        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(newUri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

  @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserRequestPayLoad payLoad) {
        User user = userService.updateUser(id, payLoad);
        return ResponseEntity.ok().body(user);
    }
}
