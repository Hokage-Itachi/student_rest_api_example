package com.example.demo.controller;


import com.example.demo.coverter.StudentConverter;
import com.example.demo.coverter.UserConverter;
import com.example.demo.domain.Course;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.domain.User;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.response.Response;
import com.example.demo.service.StudentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class StudentController {

    private final StudentService studentService;
    private final StudentConverter studentConverter;


    public StudentController(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStudent() {
        List<Student> students = studentService.findAll();
        List<StudentDto> studentDtoList = students.stream().map(this.studentConverter::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable("courseId") Integer courseId, @PathVariable("studentId") Integer studentId) {
        Optional<Student> optionalStudent = studentService.findById(studentId);
        if (optionalStudent.isEmpty()) {
            Response response = new Response();
            response.setMessage("Student " + String.valueOf(studentId) + " not found");
            response.setStatus("404");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        StudentDto studentDto = studentConverter.toDto(optionalStudent.get());
        System.out.println(studentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PostMapping("/courses/{courseId}/students")
    public ResponseEntity<Object> createNewStudent(@PathVariable("courseId") Integer courseId, @RequestBody StudentDto studentDto) {
        Response response = new Response();
        Course course = new Course(courseId, "", null, null);
        Student student = this.studentConverter.toEntity(studentDto);
        List<Course> joinedCourses = new ArrayList<>();
        joinedCourses.add(course);
        student.setJoinedCourse(joinedCourses);
        this.studentService.save(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (optionalStudent.isEmpty()) {

            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        Student s = optionalStudent.get();

        s.setName(student.getName());
        s.setPhoneNumber(student.getPhoneNumber());

        return new ResponseEntity<Student>(studentService.save(s), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") Integer id) {
        try {
            studentService.remove(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        User userDto = new User();
        Role roleDto = new Role(1, "student");

        userDto.setId(1);
        userDto.setUsername("BAn");
        userDto.setPassword("123");
        userDto.setRole(roleDto);

        UserConverter userConverter = new UserConverter();


        return new ResponseEntity<>(userConverter.toUserDto(userDto), HttpStatus.OK);

    }

}
