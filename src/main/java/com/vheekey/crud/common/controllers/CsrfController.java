package com.vheekey.crud.common.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/csrf")
public class CsrfController {

    @GetMapping
    public Map<String, String> csrf(HttpServletRequest httpServletRequest) {
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());

        return Collections.singletonMap("token", csrfToken.getToken());
    }
}
