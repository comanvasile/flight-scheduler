package com.example.assignment.controller;

import com.example.assignment.dto.AirportDTO;
import com.example.assignment.dto.IdDTO;
import com.example.assignment.dto.UserDTO;
import com.example.assignment.enumeration.UserRole;
import com.example.assignment.model.UserModel;
import com.example.assignment.service.AirportService;
import com.example.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
    private final UserService userService;
    private final AirportService airportService;
    @Autowired
    public UserRestController(UserService userService,AirportService airportService){
        this.airportService=airportService;
        this.userService=userService;
    }
    @GetMapping("/list")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }
    @PostMapping("/save")
    public Long save(@RequestBody UserModel userModel){

        UserDTO userDTO=new UserDTO();
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setPassword(userModel.getPassword());
        userDTO.setPhoneNumber(userModel.getPhone());
        userDTO.setUserRole(UserRole.SUPERVISOR);
        userDTO.setUsername(userModel.getUsername());
        return userService.save(userDTO,userModel.getAirport());
    }
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }
    @GetMapping("find/{username}")
    public UserModel findByUsername(@PathVariable("username") String username) throws Exception {
        UserDTO userDTO=userService.findByUsername(username);
       ;
        AirportDTO airport= null;
        List<AirportDTO> airportDTOS=airportService.findAll();
        for(AirportDTO airportDTO1:airportDTOS){
            List<IdDTO> list=airportDTO1.getUsers();
            for(IdDTO idDTO1: list){
                if(idDTO1.getId()==userDTO.getId()){
                    airport=airportDTO1;
                    break;
                }
            }
        }
        UserModel userModel=new UserModel();
        userModel.setEmail(userDTO.getEmail());
        userModel.setName(userDTO.getName());
        userModel.setPhone(userDTO.getPhoneNumber());
        userModel.setPassword(userDTO.getPassword());
        userModel.setUsername(userDTO.getUsername());
        userModel.setAirport(airport.getName());
        return userModel;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

}
