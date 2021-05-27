package com.example.demo.coverter;

import com.example.demo.domain.Course;
import com.example.demo.dto.CourseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    private final ModelMapper modelMapper;

    public CourseConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CourseDto toDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    public Course toEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }

}
