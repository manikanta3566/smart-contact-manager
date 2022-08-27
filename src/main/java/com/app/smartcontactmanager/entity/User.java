package com.app.smartcontactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
@Setter
@Getter
@AllArgsConstructor
public class User {
    @Id
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
    public User(){
        this.id= UUID.randomUUID().toString();
    }
}
