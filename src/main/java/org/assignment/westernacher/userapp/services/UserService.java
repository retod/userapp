package org.assignment.westernacher.userapp.services;

import org.assignment.westernacher.userapp.model.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> listAllUsers();

    UserDto createUser(UserDto user);

    Optional<UserDto> getUserById(UUID id);

    Boolean deleteUser(UUID id);

    Optional<UserDto> updateUserById(UUID id, UserDto user);


}
