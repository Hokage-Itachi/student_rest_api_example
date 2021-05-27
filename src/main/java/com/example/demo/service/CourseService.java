package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    public Optional<Course> findById(Integer id) {
        return this.courseRepository.findById(id);

    }

    public Course save(Course course) {
        return this.courseRepository.save(course);
    }

    public void remove(Integer id) {
        this.courseRepository.deleteById(id);
    }
}
