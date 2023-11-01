package com.example.apiuplay.repository;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoty<usuario> extends CrudRepository<usuario, Long> {
    UserDTO findByUsername(String username);

    UserDTO save(User user);
}
