package ru.example.urlshorter.Forms;


import lombok.Data;
import ru.example.urlshorter.Models.User;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class ShorterForm {
    private String hash;
    private String originalUrl;
    private Date createdAt;
    private String expiresDate;
    private Integer transitionCounter;
    private User owner;
    private Integer shorterLength;
}
