package com.example.apiuplay.services;

import com.example.apiuplay.models.entities.Question;
import com.example.apiuplay.models.entities.QuestionXUser;
import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.models.entities.Wallet;
import com.example.apiuplay.models.views.UserDTO;
import com.example.apiuplay.models.views.UserModifyPasswordDTO;
import com.example.apiuplay.models.views.UserRegistrationDTO;
import com.example.apiuplay.repository.QuestionRepository;
import com.example.apiuplay.repository.QuestionXUserRepository;
import com.example.apiuplay.repository.UserRepository;
import com.example.apiuplay.repository.WalletRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final QuestionXUserRepository questionXUserRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, QuestionRepository questionRepository,
                       QuestionXUserRepository questionXUserRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.questionXUserRepository = questionXUserRepository;
        this.walletRepository = walletRepository;
    }

    public User createUser(@NotNull UserRegistrationDTO userRegistrationDTO) {
        User saveUser = new User(userRegistrationDTO.getUserName(), userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(),
                userRegistrationDTO.getName(), userRegistrationDTO.getLastName(), userRegistrationDTO.getPhoneNumber());
        User existsUser = userRepository.save(saveUser);
        Question selectedQuestion = questionRepository.findById(userRegistrationDTO.getQuestionId()).orElse(null);
        QuestionXUser saveQuestionXUser = questionXUserRepository.save(new QuestionXUser(existsUser, selectedQuestion, userRegistrationDTO.getAnswer()));
        Wallet wallet = walletRepository.save(new Wallet(existsUser, 50.0, 0.0, 0.0, 0.0));
        return saveUser;
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

        return userRepository.save(saveUser);
    }

    public UserDTO getBasicDataUserDTO(User user) {

        Wallet wallet = walletRepository.findByUserId(user.getId());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastName());
        userDTO.setUtncoin(wallet.getUtncoinAmount());

        return userDTO;
    }

    public UserDTO getFullDataUserDTO(User user) {

        Wallet wallet = walletRepository.findByUserId(user.getId());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastName());
        userDTO.setPhonenumber(user.getPhoneNumber());
        userDTO.setUtncoin(wallet.getUtncoinAmount());

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

    public boolean updateCoinBalance(Long userId, double newCoinBalance) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Wallet wallet = walletRepository.findByUserId(userId);
            if(ObjectUtils.isNotEmpty(wallet)){
                wallet.setUtncoinAmount(newCoinBalance);
                return true;
            }
        }
        return false;
    }

    public double getCoinBalance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(ObjectUtils.isNotEmpty(user)){
            Wallet wallet = walletRepository.findByUserId(userId);
            return ObjectUtils.isNotEmpty(wallet) ? wallet.getUtncoinAmount() : -1;
        }
        return -1;
    }

}
