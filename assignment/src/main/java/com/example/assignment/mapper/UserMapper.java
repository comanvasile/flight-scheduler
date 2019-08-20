package com.example.assignment.mapper;

import com.example.assignment.dto.UserDTO;
import com.example.assignment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses= IdMapper.class)
public interface UserMapper {

    User toEntity(UserDTO userDTO);


    void toEntityUpdate(@MappingTarget User user,UserDTO userDTO);

    UserDTO toDTO(User user);


}
