package com.motor.carplatserver.service;

import com.motor.carplatserver.model.UserAccount;
import com.motor.carplatserver.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void registerAccount(UserAccount userAccount) throws Exception {

        if(userAccountRepository.findByEmail(userAccount.getEmail()).isPresent()){
            throw new Exception("Email Already Exists");
        }

        if(userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()){
            throw new Exception("Username Already Exists");
        }

        userAccountRepository.save(userAccount);
    }
}
