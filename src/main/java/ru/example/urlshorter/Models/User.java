package ru.example.urlshorter.Models;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String hashPassword;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Shorter> shortLinks;
}
