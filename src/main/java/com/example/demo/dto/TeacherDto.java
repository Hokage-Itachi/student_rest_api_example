package com.example.demo.dto;

import com.example.demo.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TeacherDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private UserDto user;
    private List<CourseDto> courses;
}
