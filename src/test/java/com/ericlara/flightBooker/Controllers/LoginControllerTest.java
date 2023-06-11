package com.ericlara.flightBooker.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;




@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testLogin() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(view().name("authentication/login"))
                .andDo(MockMvcResultHandlers.print());
    }
}
