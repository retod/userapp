package org.assignment.westernacher.userapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.assignment.westernacher.userapp.model.UserDto;
import org.assignment.westernacher.userapp.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {

    private static final String USER_PATH="/api/users";
    private static final String USER_PATH_ID="/api/users/{id}";

    private final UserService userService;


    @GetMapping(USER_PATH)
    public List<UserDto> getAllUsers() {

        return userService.listAllUsers();
    }

    @GetMapping(USER_PATH_ID)
    public UserDto getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id).orElseThrow(NotFoundException::new);
    }


    @PostMapping(USER_PATH)
    public ResponseEntity createUser( @Valid @RequestBody UserDto user ) {
        UserDto createdUser = userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", USER_PATH + "/" + createdUser.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(USER_PATH_ID)
    public ResponseEntity deleteUserById(@PathVariable("id") UUID id){
        if (!userService.deleteUser(id)){
            throw new NotFoundException("User with id " + id.toString() + " not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(USER_PATH_ID)
    public ResponseEntity updateUserById(@PathVariable("id") UUID id, @Valid @RequestBody UserDto user) {
        if (userService.updateUserById(id, user).isEmpty()){
            throw new NotFoundException("User with id " + id.toString() + " not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
