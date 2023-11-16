package com.example.apiuplay.repository;

import com.example.apiuplay.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);

    User save(User user);

    User findByEmail(String email);
}
