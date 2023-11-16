package com.example.apiuplay.services;

import com.example.apiuplay.models.entities.Question;
import com.example.apiuplay.models.entities.QuestionXUser;
import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.models.views.UserDTO;
import com.example.apiuplay.models.views.UserModifyPasswordDTO;
import com.example.apiuplay.models.views.UserRegistrationDTO;
import com.example.apiuplay.repository.QuestionRepository;
import com.example.apiuplay.repository.QuestionXUserRepository;
import com.example.apiuplay.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final QuestionXUserRepository questionXUserRepository;

    public UserService(UserRepository userRepository, QuestionRepository questionRepository, QuestionXUserRepository questionXUserRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.questionXUserRepository = questionXUserRepository;
    }

    public User createUser(@NotNull UserRegistrationDTO userRegistrationDTO) {
        User isExistUsername = this.findByUsername(userRegistrationDTO.getUserName());
        User isExistEmail = this.findByEmail(userRegistrationDTO.getEmail());
        if (isExistUsername == null && isExistEmail == null) {
            User saveUser = new User(userRegistrationDTO.getUserName(), userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(),
                    userRegistrationDTO.getName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getPhoneNumber());
            User existsUser = userRepository.save(saveUser);
            Question selectedQuestion = questionRepository.findById(userRegistrationDTO.getQuestionId()).orElse(null);
            QuestionXUser saveQuestionXUser = questionXUserRepository.save(new QuestionXUser(existsUser, selectedQuestion, userRegistrationDTO.getAnswer()));
            return saveUser;
        }
        return null;
    }

    public User saveUser(@NotNull UserDTO userDTO, User user) {

        User saveUser = new User();
        saveUser.setId(user.getId());
        saveUser.setUserName(ObjectUtils.isNotEmpty(userDTO.getUsername()) ? userDTO.getUsername() : user.getUserName());
        saveUser.setEmail(ObjectUtils.isNotEmpty(userDTO.getEmail()) ? userDTO.getEmail() : user.getEmail());
        saveUser.setPassword(ObjectUtils.isNotEmpty(userDTO.getPassword()) ? userDTO.getPassword() : user.getPassword());
        saveUser.setName(ObjectUtils.isNotEmpty(userDTO.getName()) ? userDTO.getName() : user.getName());
        saveUser.setLastName(ObjectUtils.isNotEmpty(userDTO.getLastname()) ? userDTO.getLastname() : user.getLastName());
        saveUser.setPhoneNumber(ObjectUtils.isNotEmpty(userDTO.getPhonenumber()) ? userDTO.getPhonenumber() : user.getPhoneNumber());
        saveUser.setUtnCoin(user.getUtnCoin());

        return userRepository.save(saveUser);
    }

    public UserDTO getBasicDataUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastName());
        userDTO.setUtncoin(user.getUtnCoin());
        return userDTO;
    }

    public UserDTO getFullDataUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastName());
        userDTO.setPhonenumber(user.getPhoneNumber());
        userDTO.setUtncoin(user.getUtnCoin());
        return userDTO;
    }

    public User modifyPassword(UserModifyPasswordDTO userModifyPasswordDTO, User user) {
        QuestionXUser questionXUser = questionXUserRepository.findByUserId(user.getId());
        if (ObjectUtils.isNotEmpty(questionXUser) && questionXUser.getAnswer().equalsIgnoreCase(userModifyPasswordDTO.getAnswer())) {
            user.setPassword(userModifyPasswordDTO.getNewPassword());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
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
            user.setUtnCoin(newCoinBalance);
            return userRepository.save(user);
        }
        return null;
    }

    public int getCoinBalance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getUtnCoin() : -1;
    }

}
