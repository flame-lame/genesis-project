package com.example.genesis.api.v1;

import com.example.genesis.api.v1.model.CreateUserRequest;
import com.example.genesis.api.v1.model.GetUserDetailResponse;
import com.example.genesis.api.v1.model.GetUserResponse;
import com.example.genesis.api.v1.model.UpdateUserRequest;
import com.example.genesis.service.DuplicatePersonIdException;
import com.example.genesis.service.UserNotFoundException;
import com.example.genesis.service.UserService;
import com.example.genesis.storage.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public final class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<Long> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(createUserRequest.name());
            userEntity.setSurname(createUserRequest.surname());
            userEntity.setPersonId(createUserRequest.personId());
            userEntity.setUuid(UUID.randomUUID());

            Long userId = userService.createUser(userEntity);

            return new ResponseEntity<>(userId, HttpStatus.CREATED);
        } catch (DuplicatePersonIdException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Person with this person ID already exists");
        }
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        List<UserEntity> allUsers = userService.findAllUsers();

        List<GetUserResponse> userResponses = allUsers.stream()
                .map(user -> new GetUserResponse(
                        user.getId(),
                        user.getName(),
                        user.getSurname()
                )).toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping(value = "/api/v1/users", params = "detail=true")
    public ResponseEntity<List<GetUserDetailResponse>> getAllUserDetails(@RequestParam(value = "detail", required = false) Boolean ignoredDetail) {
        List<UserEntity> allUsers = userService.findAllUsers();
        List<GetUserDetailResponse> userResponses = allUsers.stream()
                .map(user -> new GetUserDetailResponse(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getPersonId(),
                        user.getUuid()
                )).toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") Long id) {
        UserEntity user = userService.findUserById(id);

        if (user != null) {
            return ResponseEntity.ok(new GetUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getSurname()
            ));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping(value = "/api/v1/users/{id}", params = "detail=true")
    public ResponseEntity<GetUserDetailResponse> getUserDetailById(@PathVariable Long id, @RequestParam(value = "detail", required = false) Boolean ignoredDetail) {
        UserEntity user = userService.findUserById(id);

        if (user != null) {
            return ResponseEntity.ok(new GetUserDetailResponse(
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getPersonId(),
                    user.getUuid()
            ));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PutMapping("/api/v1/users")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        try {
            userService.updateUser(
                    updateUserRequest.id(),
                    updateUserRequest.name(),
                    updateUserRequest.surname()
            );

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
