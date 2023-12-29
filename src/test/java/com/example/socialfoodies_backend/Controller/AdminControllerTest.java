package com.example.socialfoodies_backend.Controller;

import com.example.socialfoodies_backend.controller.AdminController;
import com.example.socialfoodies_backend.model.Admin;
import com.example.socialfoodies_backend.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AdminControllerTest {

    private final String LOGIN_ENDPOINT = "/admins/login";

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @Test
    public void testLoginSuccessful() throws Exception {

        String email = "admin@example.com";
        String password = "password";
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        when(adminService.login(email, password)).thenReturn(Optional.of(admin));

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        //Indskriv oplysninger
        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@example.com\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Login successful"));
    }

    @Test
    public void testLoginFailed() throws Exception {

        String email = "admin@example.com";
        String password = "password";
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        when(adminService.login(email, password)).thenReturn(Optional.empty());

        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        //Indskriv oplysninger
        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@example.com\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Login failed"));
    }
}
