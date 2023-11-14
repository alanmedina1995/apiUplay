package com.example.apiuplay.repository;

import com.example.apiuplay.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User save(User user);
}
