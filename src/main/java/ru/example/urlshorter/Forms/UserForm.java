package ru.example.urlshorter.Forms;

import lombok.Data;

@Data
public class UserForm {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private String role;

}
