package com.example.demo.coverter;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private ModelMapper modelMapper;
    public User toUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);

    }

    public UserDto toUserDto(User user) {
        return this.modelMapper.map(user, UserDto.class);

    }

}
