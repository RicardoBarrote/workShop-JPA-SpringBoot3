package com.projectworkshop.workshop.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectworkshop.workshop.entities.User;

@RestController
@RequestMapping (value = "/users")
public class UserResource {
	//Tipo ResponseEntity<T> -> Retorna respostas de requisições web.
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		User user = new User(1L, "Ricardo Barrote", "Ricardo@gmail.com", "99999999999", "12345");
		return ResponseEntity.ok().body(user);
	}
	
	
}
