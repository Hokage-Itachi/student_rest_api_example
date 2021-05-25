package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

}

