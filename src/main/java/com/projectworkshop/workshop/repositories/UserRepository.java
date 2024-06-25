package com.projectworkshop.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectworkshop.workshop.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
