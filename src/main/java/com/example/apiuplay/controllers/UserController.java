package com.example.apiuplay.controllers;

import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.models.entities.Wallet;
import com.example.apiuplay.models.views.*;
import com.example.apiuplay.services.JwtService;
import com.example.apiuplay.services.ResendService;
import com.example.apiuplay.services.TransactionService;
import com.example.apiuplay.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    private final ResendService resendService = new ResendService();

    private final TransactionService transactionService;

    public UserController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        HttpHeaders headers = new HttpHeaders();
        User existsMail = userService.findByEmail(userRegistrationDTO.getEmail());
        if (ObjectUtils.isEmpty(existsMail)) {
            User existsUserName = userService.findByUsername((userRegistrationDTO.getUserName()));
            if (ObjectUtils.isEmpty(existsUserName)) {
                User savedUser = userService.createUser(userRegistrationDTO);// Guarda el usuario en el repositorio
                if (savedUser == null) {
                    headers.add("Header", "FAIL");
                    return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
                }
                headers.add("Header", "OK");
                this.resendService.sendMailRegister(savedUser.getName());
                UserDTO userResponse = userService.getBasicDataUserDTO(savedUser);
                return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
            } else {
                headers.add("Header", "FAIL");
                headers.add("Error-Message", "The userName already exists. It is not possible to create the user.");
                return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
            }
        } else {
            headers.add("Header", "FAIL");
            headers.add("Error-Message", "The email already exists. It is not possible to create the user.");
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
    }



    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        User findUser = userService.findByUsername(userDTO.getUsername());
        if (ObjectUtils.isEmpty(findUser)) {
            findUser = userService.findByEmail(userDTO.getUsername());
        }
        HttpHeaders headers = new HttpHeaders();

        if (findUser != null && findUser.getPassword().equals(userDTO.getPassword())) {

            headers.add("Header", "OK");
            UserDTO responseDTO = userService.getBasicDataUserDTO(findUser);

            String token = JwtService.generateToken(responseDTO.getUsername());

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String userData = objectMapper.writeValueAsString(responseDTO);
                responseBody.put("userData", userData);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                headers.add("Header", "FAIL");
                return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
        }

        headers.add("Header", "FAIL");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update-coin-balance/{userId}")
    public ResponseEntity<String> updateCoinBalance(
            @PathVariable Long userId,
            @RequestParam Double newCoinBalance
    ) {
        HttpHeaders headers = new HttpHeaders();
        boolean isUpdated = userService.updateCoinBalance(userId, newCoinBalance);

        if (Boolean.TRUE.equals(isUpdated)) {
            headers.add("Header", "OK");
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/coin-balance/{userId}")
    public ResponseEntity<Double> getCoinBalance(@PathVariable Long userId) {
        HttpHeaders headers = new HttpHeaders();
        double coinBalance = userService.getCoinBalance(userId);

        if (coinBalance >= 0) {
            headers.add("Header", "OK");
            return new ResponseEntity<>(coinBalance, headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(coinBalance, headers, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            User existsUserName = userService.findByUsername(updatedUser.getUsername());
            User existsEmail = userService.findByEmail(updatedUser.getEmail());
            boolean sameUsername = existingUser.getUsername().equals(updatedUser.getUsername());
            boolean sameEmail = existingUser.getEmail().equals(updatedUser.getEmail());
            if(Boolean.FALSE.equals(sameUsername) && ObjectUtils.isNotEmpty(existsUserName)){
                headers.add("Header", "FAIL");
                return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
            }
            if(Boolean.FALSE.equals(sameEmail) && ObjectUtils.isNotEmpty(existsEmail)){
                headers.add("Header", "FAIL");
                return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
            }
            User savedUser = userService.saveUser(updatedUser, existingUser);
            return getUserDTOResponseEntity(headers, savedUser);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifyPassword")
    public ResponseEntity<UserDTO> modifyPassword(@RequestBody UserModifyPasswordDTO userModifyPasswordDTO) {
        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findByEmail(userModifyPasswordDTO.getEmail());
        if (ObjectUtils.isNotEmpty(existingUser)) {
            User updatedUser = userService.modifyPassword(userModifyPasswordDTO, existingUser);
            this.resendService.sendMailModifyPassword(updatedUser.getName());
            return getUserDTOResponseEntity(headers, updatedUser);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @NotNull
    private ResponseEntity<UserDTO> getUserDTOResponseEntity(HttpHeaders headers, User savedUser) {
        if (ObjectUtils.isNotEmpty(savedUser)) {
            UserDTO userResponse = userService.getBasicDataUserDTO(savedUser);
            headers.add("Header", "OK");
            return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/exchange-coins")
    public ResponseEntity<String> exchangeCoins(@RequestBody ExchangeRequest exchangeRequest) {
        try {
            User user = userService.findById(exchangeRequest.getUserId());

            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            double currentDollarBlueValue = exchangeRequest.getCurrentDollarBlueValue();
            double currentCryptoValue = exchangeRequest.getCurrentCryptoValue();

            boolean exchangeSuccess = userService.exchangeCoins(
                    exchangeRequest.getUserId(),
                    exchangeRequest.getCryptoAmount(),
                    exchangeRequest.getCryptocurrency()
            );

            if (exchangeSuccess) {
                transactionService.createTransaction(user, exchangeRequest.getAmount(),
                        currentDollarBlueValue, currentCryptoValue, exchangeRequest.getCryptocurrency(), exchangeRequest.getCryptoAmount());

                return ResponseEntity.ok("Coin exchange successful");
            } else {
                return ResponseEntity.badRequest().body("Coin exchange failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<TransactionDTO>> getUserTransactions(@PathVariable Long userId) {
        List<TransactionDTO> transactions = transactionService.getUserTransactions(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/data/{userId}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable Long userId){
        HttpHeaders headers = new HttpHeaders();
        User user = userService.findById(userId);
        if(ObjectUtils.isNotEmpty(user)){
            UserDTO userResponse = userService.getFullDataUserDTO(user);
            headers.add("Header", "OK");
            return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
        }
        headers.add("Header", "FAIL");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/wallet/{userId}")
    public ResponseEntity<Wallet> getUserWallet(@PathVariable Long userId) {
        HttpHeaders headers = new HttpHeaders();
        Wallet userWallet = userService.getUserWallet(userId);

        if (userWallet != null) {
            headers.add("Header", "OK");
            return new ResponseEntity<>(userWallet, headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }


}



