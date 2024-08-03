package com.vheekey.crud.employee.interfaces;

public interface PasswordServiceInterface {

    String encodePassword(String inputtedPassword);

    boolean matchPassword(String inputtedPassword, String encodedPassword);
}
