package com.ericlara.flightBooker.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Models.UserXML;
import com.ericlara.flightBooker.Services.UserService;
import com.ericlara.flightBooker.Services.UserXMLService;
import com.ericlara.flightBooker.util.TbConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;

@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "userXMLService")
    private UserXMLService userXMLService;

    @MockBean(name = "userService")
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private UserXML user;

    private List<UserXML> users;

    private UserEntity userEntity;

    private String userXML;

    @BeforeEach
    void setUp() {
    
        //Creating new user
        user = new UserXML("userFirstName",
                "userLastName",
                "user@email.com",
                "password");

        users = List.of(user);

        //Creating a role for the booking user
        Role role = new Role(TbConstants.Roles.USER);
        //Creating a user for booking
        userEntity = new UserEntity("userFirstName", 
            "userLastName", 
            "user@email.com",
            "password",
            Arrays.asList(role), 
            new HashSet<>());
        userEntity.setId(1L);

        
        try {
            StringWriter sw = new StringWriter();
            //create JAXB context
            JAXBContext context = JAXBContext.newInstance(UserXML.class);
            //Create Marshaller using JAXB context
            Marshaller marshaller = context.createMarshaller();
         
            //Do the marshal operation
            marshaller.marshal(user, sw);
            userXML = sw.toString();
            } catch (Exception e) {
            e.printStackTrace();
            }


    }

    @Test
    void testGetUserById() throws JsonProcessingException, Exception {
        // Set up mock data
        Long userId = 1L;
        BDDMockito.given(userXMLService.findUserById(any(Long.class))).willReturn(user);
        Mockito.when(userXMLService.findUserById(userId)).thenReturn(user);
        
        // Perform the request and assert that the the response XML is correct
        mockMvc.perform(get(UserRestController.USER_PATH_ID, userId)
            .accept(MediaType.APPLICATION_XML_VALUE)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .content(objectMapper.writeValueAsString(user))
            .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetUsers() throws JsonProcessingException, Exception {
        // Set up mock data
        BDDMockito.given(userXMLService.findAllUsers()).willReturn(users);
        Mockito.when(userXMLService.findAllUsers()).thenReturn(users);
        // Perform the request and assert that the response XML is correct.
        mockMvc.perform(get(UserRestController.USER_PATH)
            .accept(MediaType.APPLICATION_XML)
            .contentType(MediaType.APPLICATION_XML)
            .content(objectMapper.writeValueAsString(users)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testRegisterUser() throws Exception {
        //Set up mock data
        String userEmail = "user@email.com";
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(userEntity);
        Mockito.when(userService.findUserByEmail(userEmail)).thenReturn(userEntity);

        // Perform the request and assert that the response code is correct.
        mockMvc.perform(post(UserRestController.USER_PATH)
            .accept(MediaType.APPLICATION_XML)
            .contentType(MediaType.APPLICATION_XML)
            .content(userXML)
            .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andDo(MockMvcResultHandlers.print());

    }
}
