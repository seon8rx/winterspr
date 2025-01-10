package com.thc.winterspr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter @Column(unique=true) String username;
    @Setter String password;

    @Setter String name;
    @Setter String nick;
    @Setter String phone;
}
