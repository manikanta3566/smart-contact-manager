package com.app.smartcontactmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    private String email;

    private String work;

    private String phone;

    private String imageUrl;

    @Column(length = 500)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
