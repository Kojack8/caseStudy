package com.controller;

import com.userdetails.CustomUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class SecurityController {

    String username = null;
    Collection authorities = null;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            username = userPrincipal.getFullName();
        } else {
            username = null;
        }
        return username;
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public Collection currentAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
            authorities = userPrincipal.getAuthorities();
        } else {
            authorities = null;
        }
        return authorities;
    }
}
