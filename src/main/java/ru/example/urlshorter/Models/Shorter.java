package ru.example.urlshorter.Models;


import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shorter")
public class Shorter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String hash;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;


    @Column(name = "expires_date", columnDefinition = "TIMESTAMP")
    private Date expiresDate;

    @Column(name = "transitionCounter")
    private Integer transitionCounter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "shorter_length")
    private Integer shorterLength;

    @Override
    public String toString() {
        return "Original url - " + originalUrl
                + ", Short url - " + hash
                + ", Created at - " + createdAt
                + ", Expires date - " + expiresDate
                + ", Transition Counter - " + transitionCounter
                + ", Owner - " + owner.getFirstName();
    }
}