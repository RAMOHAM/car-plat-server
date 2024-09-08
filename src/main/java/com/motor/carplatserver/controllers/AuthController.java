package com.motor.carplatserver.controllers;

import com.motor.carplatserver.model.UserAccount;
import com.motor.carplatserver.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAccount userAccount) {
        try{
            userAccountService.registerAccount(userAccount);
            return ResponseEntity.ok("Account registered successfully");

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
