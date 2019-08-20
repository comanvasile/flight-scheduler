package com.example.assignment.service.impl;

import com.example.assignment.dto.UserDTO;
import com.example.assignment.entity.User;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.repository.AirportRepository;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AirportRepository airportRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper,AirportRepository airportRepository){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
        this.airportRepository=airportRepository;
    }
    @Override
    public UserDTO findById(Long id) {
        final User user=userRepository.findById(id);
              //  .orElseThrow(()->new EntityNotFoundException("Cannot find user with  "+id));
        return userMapper.toDTO(user);

    }
    @Override
    public UserDTO findByUsername(String username){
        final User user=userRepository.findByUsername(username);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Long save(UserDTO userDTO,String airport) {
        User user;
        if(userRepository.findById(userDTO.getId())!=null){
            user=userRepository.findById(userDTO.getId());
                    //.orElseThrow(EntityNotFoundException::new);

        }
        else{
            user=new User();
        }
        airportRepository.findByName(airport).getUsers().add(user);
        userMapper.toEntityUpdate(user,userDTO);
        return userMapper.toDTO(userRepository.save(user)).getId();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        final User user=userRepository.findByUsername(userDTO.getUsername());
        if(user!=null){
            return userMapper.toDTO(user);
        }
        else{
            return null;

        }

    }
}
