package com.app.smartcontactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Setter
@Getter
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @NotNull(message = "Name field should not be empty")
    @Size(min = 2,max = 20,message = "Name should contains minimum 2 characters and maximum 20 characters")
    private String name;

    @Pattern(regexp = "(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})",message = "Invalid Email")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "password must contain atleast 8 characters,1 number,1 upper case letter,1 lower case letter,1 special character,password must not contain any spaces")
    private String password;

    private boolean active;

    @NotNull(message = "Should not be blank please fill the information")
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
