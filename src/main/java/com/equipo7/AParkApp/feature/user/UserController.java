package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController {
    private final IUserService userService;

    @GetMapping
    ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/{email}")
    ResponseEntity<UserResponse> findById(@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PostMapping
    ResponseEntity<UserResponse> create(@RequestBody UserRequest user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @PutMapping
    ResponseEntity<UserResponse> update(@RequestBody UserRequest user){
        return ResponseEntity.ok(userService.update(user));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
