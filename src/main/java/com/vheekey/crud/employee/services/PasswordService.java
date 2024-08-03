package com.vheekey.crud.employee.services;

import com.vheekey.crud.employee.interfaces.PasswordServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements PasswordServiceInterface {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String inputtedPassword) {
        return this.passwordEncoder.encode(inputtedPassword);
    }

    @Override
    public boolean matchPassword(String inputtedPassword, String encodedPassword) {
        return this.passwordEncoder.matches(inputtedPassword, encodedPassword);
    }
}
