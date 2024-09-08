package com.motor.carplatserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motor.carplatserver.model.UserAccount;
import com.motor.carplatserver.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserAccountService userAccountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registerUserTest() throws Exception {
        UserAccount userAccount = new UserAccount("Joe", "password", "joe.smith@gmail.com");
        doNothing().when(userAccountService).registerAccount(any(UserAccount.class));

        mvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userAccount)))
                .andExpect(status().isOk())
                .andExpect(content().string("Account registered successfully"));
    }
}