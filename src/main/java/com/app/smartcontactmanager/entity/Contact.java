package com.app.smartcontactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CONTACTS")
@AllArgsConstructor
@Setter
@Getter
public class Contact {
    @Id
    private String id;

    private String name;

    private String email;

    private String work;

    private String phone;

    private String imageName;

    @Column(length = 500)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Contact(){
        this.id= UUID.randomUUID().toString();
    }

}
