package com.app.smartcontactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean active;

    @Column(length = 500)
    private String about;

    private String imageUrl;

    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Contact> contacts;
}
