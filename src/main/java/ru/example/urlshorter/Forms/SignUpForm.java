package ru.example.urlshorter.Forms;

import lombok.Data;

@Data
public class SignUpForm {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}