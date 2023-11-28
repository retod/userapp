package org.assignment.westernacher.userapp.services;

import lombok.RequiredArgsConstructor;
import org.assignment.westernacher.userapp.mappers.UserMapper;
import org.assignment.westernacher.userapp.model.UserDto;
import org.assignment.westernacher.userapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> listAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto user) {
        return UserMapper.userToUserDto(userRepository.save(UserMapper.userDtoToUser(user)));
    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return Optional.ofNullable(UserMapper.userToUserDto(userRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public Boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserDto> updateUserById(UUID id, UserDto user) {
        AtomicReference<Optional<UserDto>> atomicReference = new AtomicReference<>();
        userRepository.findById(id).ifPresentOrElse(
                existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setBirthDate(user.getBirthDate());
                    atomicReference.set(Optional.of(UserMapper.userToUserDto(userRepository.save(existingUser))));
                }, () -> {
                    atomicReference.set(Optional.empty());
                });

        return atomicReference.get();
    }


}
