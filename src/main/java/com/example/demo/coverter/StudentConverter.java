package com.example.demo.coverter;

import com.example.demo.domain.Student;
import com.example.demo.dto.StudentDto;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    private final ModelMapper modelMapper;

    public StudentConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Student toEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public StudentDto toDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }
}
