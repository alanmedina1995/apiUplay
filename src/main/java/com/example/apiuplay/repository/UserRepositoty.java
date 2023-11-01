package com.example.apiuplay.repository;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoty extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User save(User user);
}
