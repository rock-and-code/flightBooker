package com.ericlara.flightBooker.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Services.UserService;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionInfo {

    private UserEntity user;
    @Autowired
    private UserService userService;

    public UserEntity getCurrentUser() {
        User UserInfo = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = UserInfo.getName();
        if (user == null) {
            user = userService.findUserByEmail(email);
        }
        return user;
    }
}