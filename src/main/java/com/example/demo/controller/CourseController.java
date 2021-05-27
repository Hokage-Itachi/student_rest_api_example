package com.example.demo.controller;

import com.example.demo.coverter.CourseConverter;
import com.example.demo.domain.Course;
import com.example.demo.dto.CourseDto;
import com.example.demo.response.Response;
import com.example.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public CourseController(CourseService courseService, CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getAllCourse() {
        List<Course> courses = this.courseService.findAll();
        List<CourseDto> courseDtoList = courses.stream().map(this.courseConverter::toDto).collect(Collectors.toList());
        Response response = new Response();
        response.setMessage("Success");
        response.setStatus("00");
        response.setData(Collections.singletonList(courseDtoList));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable("id") Integer id) {
        Optional<Course> optionalCourse = this.courseService.findById(id);
        Response response = new Response();
        if (optionalCourse.isEmpty()) {
            response.setMessage("Course " + String.valueOf(id) + " not found");
            response.setStatus("04");
            response.setData(new ArrayList<>());
        } else {
            response.setMessage("Success");
            response.setStatus("00");
            List<Course> data = new ArrayList<>();
            data.add(optionalCourse.get());
            response.setData(Collections.singletonList(data));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<Object> addCourse(@RequestBody CourseDto courseDto) {
        Course course = this.courseConverter.toEntity(courseDto);
        Course result = this.courseService.save(course);
        Response response = new Response();

        if (result == null) {
            response.setMessage("Insert failed");
            response.setStatus("01");
        } else {
            response.setMessage("Success");
            response.setStatus("00");
        }
        response.setData(new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
