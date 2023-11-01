package com.example.apiuplay.services;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import com.example.apiuplay.repository.UserRepositoty;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositoty userRepositoty;

    public UserService(UserRepositoty userRepository) {
        this.userRepositoty = userRepository;
    }

    public UserDTO saveUser(@NotNull UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getLastname());
        return userRepositoty.save(user);
    }

    public UserDTO findByUsername(String username) {
        return userRepositoty.findByUsername(username);
    }

}
