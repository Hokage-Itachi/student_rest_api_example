package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (optionalStudent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalStudent.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
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


}
