package com.example.demo.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
