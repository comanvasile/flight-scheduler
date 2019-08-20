package com.example.assignment.controller;

import com.example.assignment.dto.AirportDTO;
import com.example.assignment.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportRestController {
    private final AirportService airportService;
    @Autowired
    public AirportRestController(AirportService airportService){
        this.airportService=airportService;
    }
    @GetMapping("/list")
    public List<AirportDTO> findAll(){
        return airportService.findAll();
    }
    @PostMapping("/save")
    public Long save(@RequestBody AirportDTO airportDTO){

        return airportService.save(airportDTO);
    }
    @GetMapping("/{id}")
    public AirportDTO findById(@PathVariable("id") Long id){
        return airportService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        airportService.delete(id);
    }
}
