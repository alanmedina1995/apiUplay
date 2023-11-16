package com.example.apiuplay.services;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import com.example.apiuplay.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@NotNull UserDTO userDTO) {
        User isExistUsername = this.findByUsername(userDTO.getUsername());
        User isExistEmail = this.findByEmail(userDTO.getEmail());
        if (isExistUsername == null && isExistEmail == null) {
            User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(),
                    userDTO.getName(), userDTO.getLastname(), userDTO.getPhonenumber());
            return userRepository.save(user);
        }
        return null;
    }

    public User saveUser(@NotNull UserDTO userDTO, User user) {

        User saveUser = new User();
        saveUser.setId(user.getId());
        saveUser.setUsername(ObjectUtils.isNotEmpty(userDTO.getUsername()) ? userDTO.getUsername() : user.getUsername());
        saveUser.setEmail(ObjectUtils.isNotEmpty(userDTO.getEmail()) ? userDTO.getEmail() : user.getEmail());
        saveUser.setPassword(ObjectUtils.isNotEmpty(userDTO.getPassword()) ? userDTO.getPassword() : user.getPassword());
        saveUser.setName(ObjectUtils.isNotEmpty(userDTO.getName()) ? userDTO.getName() : user.getName());
        saveUser.setLastname(ObjectUtils.isNotEmpty(userDTO.getLastname()) ? userDTO.getLastname() : user.getLastname());
        saveUser.setPhonenumber(ObjectUtils.isNotEmpty(userDTO.getPhonenumber()) ? userDTO.getPhonenumber() : user.getPhonenumber());
        saveUser.setUtncoin(user.getUtncoin());

        return userRepository.save(saveUser);
    }

    public UserDTO getBasicDataUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUtncoin(user.getUtncoin());
        return userDTO;
    }

    public UserDTO getFullDataUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setPhonenumber(user.getPhonenumber());
        userDTO.setUtncoin(user.getUtncoin());
        return userDTO;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateCoinBalance(Long userId, int newCoinBalance) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setUtncoin(newCoinBalance);
            return userRepository.save(user);
        }
        return null;
    }

    public int getCoinBalance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getUtncoin() : -1;
    }


}
