package com.project.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.datajpa.model.User;
import com.project.spring.datajpa.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/leader")
    public ResponseEntity<List<User>> getLeaderBoard() {
		try {
			List<User> users = new ArrayList<User>();

            users = userRepository.findAllUsersSortedByScoreDesc();

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
            User savedUser = userRepository.save(user);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
		try {
            User oldUser = userRepository.findById(id).get();
            oldUser.setAge(user.getAge());
            oldUser.setName(user.getName());
            oldUser.setProfileImage(user.getProfileImage());
            oldUser.setScore(user.getScore());
            oldUser.setUsername(user.getUsername());
            User savedUser = userRepository.save(oldUser);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
