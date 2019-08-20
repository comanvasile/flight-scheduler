package com.example.assignment.controller;

import com.example.assignment.dto.AirlineDTO;
import com.example.assignment.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
@CrossOrigin(origins = "http://localhost:4200")
public class AirlineRestController {
    private final AirlineService airlineService;

    @Autowired
    public AirlineRestController(AirlineService airlineService){
        this.airlineService=airlineService;
    }
    @GetMapping("/list")
    public List<AirlineDTO> findAll(){
        return airlineService.findAll();
    }
    @PostMapping("/save")
    public Long save(@RequestBody AirlineDTO airlineDTO){

        return airlineService.save(airlineDTO);
    }
    @GetMapping("/{id}")
    public AirlineDTO findById(@PathVariable("id") Long id){
        return airlineService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        airlineService.delete(id);
    }
}
