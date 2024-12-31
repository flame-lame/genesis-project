package com.example.genesis.service;

import com.example.genesis.storage.UserEntity;
import com.example.genesis.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public final class UserService {
    private final UserRepository userRepository;

    public Long createUser(UserEntity user) throws DuplicatePersonIdException {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatePersonIdException("Person with person ID " + user.getPersonId() + " already exists", e);
        }

        return user.getId();
    }

    public UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long userId, String name, String surname) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User " + userId + " not found"));

        userEntity.setName(name);
        userEntity.setSurname(surname);

        userRepository.save(userEntity);
    }
}
