package com.ericlara.flightBooker.Controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.naming.AuthenticationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.authentication.AuthenticationManager;

import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;

@WebMvcTest(BookingsController.class)
@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
public class BookingsControllerTest {
    // @Autowired
    // private MockMvc mockMvc;

    // @Autowired
    // private WebApplicationContext context;

    @Autowired
    private BookingsController bookingsController;

    @MockBean
    private TestRestTemplate restTemplate;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean(name = "userService")
    private UserService userService;

    @MockBean(name = "flightService")
    private FlightService flightService;

    @MockBean(name = "flightBookService")
    private FlightBookService flightBookService;

    

    /**
     * @throws Exception
     */
    @Test
    public void checkoutFormTest() throws Exception {
        assertThrows(AuthenticationException.class, ()->{
            bookingsController.checkoutForm(1L, null, null, null, null);
        });
        
        // this.mockMvc.perform(get("/bookings/flights/1")
        // // .contentType(MediaType.TEXT_HTML)
        // // .content(html)
        // // .characterEncoding("utf-8") //TO ADD JSON TO THE RESPONSE BOY  
        // ).andExpect(view().name("bookings/bookingCheckoutForm"))
        // .andDo(MockMvcResultHandlers.print());
   


    }

    @Test
    void testBookFlight() {

    }
}
