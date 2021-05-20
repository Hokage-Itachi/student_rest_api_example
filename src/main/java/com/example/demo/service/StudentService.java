package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository iStudentRepository;

    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    public Optional<Student> findById(Integer id) {
        return iStudentRepository.findById(id);
    }

    public Student save(Student student) {
        return iStudentRepository.save(student);
    }

    public void remove(Integer id) {
        iStudentRepository.deleteById(id);
    }
}
