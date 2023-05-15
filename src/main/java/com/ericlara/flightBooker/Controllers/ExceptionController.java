package com.ericlara.flightBooker.Controllers;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This class make the exception handlers whithin this class to be global for all the controllers
 * Ideal to centralize the project's exception handling
 */
@ControllerAdvice   
public class ExceptionController {
        //TO REDIRECT USER TO THE SEARCH FLIGHT FORM THAT PERFORMS GET REQUEST WITHOUT PARAMETERS ON /flights PATH 
    // (BY TYPING IT DIRECTLY IN THE URL BAR) SINCE THIS PATH SHOULD BE ACCESS EITHER BY POST REQUEST AFTER SUBMITING THE 
    //FLIGHT SEARCH FORM OR BY CLICKING FLIGHTS ON THE BREAD CRUM NAVIGATION 
    //ON THE FLIGHT DETAILS VIEW, WHICH AUTOMATICALLY WILL POPULATE THE PARAMETERS TO ALLOWS
    //USER TO GO BACK FROM FLIGHT DETAILS TO FLIGHTS AFTER SUMBITING THE FLIGHT SEARCH FORM
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RedirectView handleMissingParams() {
        return new RedirectView("/");
    }
}
