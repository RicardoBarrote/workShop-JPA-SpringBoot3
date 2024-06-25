package com.projectworkshop.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectworkshop.workshop.entities.User;
import com.projectworkshop.workshop.services.UserService;

@RestController
@RequestMapping (value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	//Tipo ResponseEntity<T> -> Retorna respostas de requisições web.
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		List<User> listUser = userService.findAll();
		return ResponseEntity.ok().body(listUser);			
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById (@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
}
