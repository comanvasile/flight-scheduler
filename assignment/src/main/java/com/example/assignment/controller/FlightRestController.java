package com.example.assignment.controller;

import com.example.assignment.dto.AirlineDTO;
import com.example.assignment.dto.AirportDTO;
import com.example.assignment.dto.FlightDTO;
import com.example.assignment.model.FlightModel;
import com.example.assignment.model.FlightModelUser;
import com.example.assignment.service.AirlineService;
import com.example.assignment.service.AirportService;
import com.example.assignment.service.FlightService;
import com.example.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightRestController {
    private final FlightService flightService;
    private final AirlineService airlineService;
    private final AirportService airportService;
    private final UserService userService;
    @Autowired
    public FlightRestController(UserService userService,FlightService flightService,AirportService airportService,AirlineService airlineService){
        this.flightService=flightService;
        this.airlineService=airlineService;
        this.airportService=airportService;
        this.userService=userService;
    }
    @GetMapping("/list")
    public List<FlightModel> findAll(){
        List<FlightModel> flights=new ArrayList<FlightModel>();
        for(FlightDTO flightDTO: flightService.findAll()){
            FlightModel flight=new FlightModel();
            flight.setId(flightDTO.getId());
            flight.setDeparture(flightDTO.getDeparture().toString());
            flight.setAirline(airlineService.findById(flightDTO.getAirline().getId()).getName());
            flight.setArrival(flightDTO.getArrival().toString());
            flight.setDestination(airportService.findById(flightDTO.getDestination().getId()).getName());
            flight.setHaltDuration(flightDTO.getHaltDuration().toString());
            flight.setHaltStation(airportService.findById(flightDTO.getHaltStation().getId()).getName());
            flight.setSource(airportService.findById(flightDTO.getSource().getId()).getName());
            flights.add(flight);
        }
        return flights;
    }
    @PutMapping("/delay/{id}")
    public Long updateDelay(@RequestBody FlightModel flightModel,@PathVariable("id") Long id){
        FlightDTO flightDTO=flightService.findById(id);
       // FlightDTO flightDTO=flightService.findById(id);
   /*     AirportDTO source=airportService.findByName(flightModel.getSource());
        AirportDTO destination=airportService.findByName(flightModel.getDestination());
        AirportDTO haltStation=airportService.findByName(flightModel.getHaltStation());
        AirlineDTO airline=airlineService.findByName(flightModel.getAirline());
        flightDTO.setId(id);
        flightDTO.setSource(source);
        flightDTO.setDestination(destination);
        flightDTO.setHaltStation(haltStation);
        flightDTO.setAirline(airline);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time arrival=null,departure=null,haltDuration=null;
        try{
            arrival=new Time(formatter.parse(flightModel.getArrival()).getTime());
            departure=new Time(formatter.parse(flightModel.getDeparture()).getTime());
            haltDuration=new Time(formatter.parse(flightModel.getHaltDuration()).getTime());
        }catch(ParseException e){
           e.printStackTrace();

       }
        flightDTO.setArrival(arrival);
       flightDTO.setDeparture(departure);flightDTO.setHaltDuration(haltDuration);*/
       DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time delay1=null;
        try {
            delay1=new Time(formatter.parse(flightModel.getDelay()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flightDTO.setDelay(delay1);
        return flightService.save(flightDTO);

    }
    @GetMapping("/search")
    public List<FlightModelUser> findSearch() throws ParseException {
        List<FlightModelUser> flights=new ArrayList<>();
        List<FlightDTO> flightDTOList=flightService.findAll();
        for(FlightDTO flightDTO: flightDTOList){
            FlightModelUser f=new FlightModelUser();
            f.setDestination(airportService.findById(flightDTO.getDestination().getId()).getName());
            f.setId(flightDTO.getId());
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time newArrival=null;
            if(flightDTO.getDelay()!=null){
                Long time=flightDTO.getDelay().getTime()+flightDTO.getArrival().getTime();
                newArrival=new Time(time);
            }
            else{
                newArrival=flightDTO.getArrival();
            }
            f.setArrival(newArrival.toString());
            Time duration=null;
            Long time2=newArrival.getTime()-flightDTO.getDeparture().getTime();
            duration=new Time(time2);
            f.setDuration(duration.toString());
            f.setSource(airportService.findById(flightDTO.getSource().getId()).getName());
            f.setAirline(airlineService.findById(flightDTO.getAirline().getId()).getName());
            flights.add(f);

        }
        return flights;
    }
    @GetMapping("/list/{username}")
    public List<FlightModel> findAllByUsername(@PathVariable("username") String username){
        List<FlightModel> flights=new ArrayList<FlightModel>();
        Long id=airportService.findByName(username).getId();
        List<FlightDTO> flightDTOList= flightService.findAll().stream().filter(f->(f.getSource().getId()==id||f.getHaltStation().getId()==id||f.getDestination().getId()==id)).collect(Collectors.toList());
            for(FlightDTO flightDTO: flightDTOList){
            FlightModel flight=new FlightModel();
            flight.setId(flightDTO.getId());
            flight.setDeparture(flightDTO.getDeparture().toString());
            flight.setAirline(airlineService.findById(flightDTO.getAirline().getId()).getName());
            flight.setArrival(flightDTO.getArrival().toString());
            flight.setDestination(airportService.findById(flightDTO.getDestination().getId()).getName());
            flight.setHaltDuration(flightDTO.getHaltDuration().toString());
            flight.setHaltStation(airportService.findById(flightDTO.getHaltStation().getId()).getName());
            flight.setSource(airportService.findById(flightDTO.getSource().getId()).getName());
            if(flightDTO.getDelay()!=null)
            flight.setDelay(flightDTO.getDelay().toString());
            flights.add(flight);
        }
        return flights;
    }
    @PostMapping("/save")
    public Long save(@RequestBody FlightModel flightModel){
        FlightDTO flightDTO=new FlightDTO();
        AirportDTO source=airportService.findByName(flightModel.getSource());
        AirportDTO destination=airportService.findByName(flightModel.getDestination());
        AirportDTO haltStation=airportService.findByName(flightModel.getHaltStation());
        AirlineDTO airline=airlineService.findByName(flightModel.getAirline());
        flightDTO.setSource(source);
        flightDTO.setDestination(destination);
        flightDTO.setHaltStation(haltStation);
        flightDTO.setAirline(airline);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time arrival=null,departure=null,haltDuration=null;
        try{
             arrival=new Time(formatter.parse(flightModel.getArrival()).getTime());
             departure=new Time(formatter.parse(flightModel.getDeparture()).getTime());
             haltDuration=new Time(formatter.parse(flightModel.getHaltDuration()).getTime());
        }catch(ParseException e){
            e.printStackTrace();

        }
        flightDTO.setArrival(arrival);
        flightDTO.setDeparture(departure);
        flightDTO.setHaltDuration(haltDuration);


        return flightService.save(flightDTO);
    }
    @GetMapping("/{id}")
    public FlightModel findById(@PathVariable("id") Long id){
        FlightDTO flightDTO=flightService.findById(id);
        FlightModel flight=new FlightModel();
        flight.setId(flightDTO.getId());
        flight.setDeparture(flightDTO.getDeparture().toString());
        flight.setAirline(airlineService.findById(flightDTO.getAirline().getId()).getName());
        flight.setArrival(flightDTO.getArrival().toString());
        flight.setDestination(airportService.findById(flightDTO.getDestination().getId()).getName());
        flight.setHaltDuration(flightDTO.getHaltDuration().toString());
        flight.setHaltStation(airportService.findById(flightDTO.getHaltStation().getId()).getName());
        flight.setSource(airportService.findById(flightDTO.getSource().getId()).getName());
        return flight;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        flightService.delete(id);
    }
    @PutMapping("{id}")
    public Long updateFlight(@RequestBody FlightModel flightModel,
                                           @PathVariable(value = "id") Long id) {

            FlightDTO flightDTO=flightService.findById(id);
        AirportDTO source=airportService.findByName(flightModel.getSource());
        AirportDTO destination=airportService.findByName(flightModel.getDestination());
        AirportDTO haltStation=airportService.findByName(flightModel.getHaltStation());
        AirlineDTO airline=airlineService.findByName(flightModel.getAirline());
        flightDTO.setSource(source);
        flightDTO.setDestination(destination);
        flightDTO.setHaltStation(haltStation);
        flightDTO.setAirline(airline);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time arrival=null,departure=null,haltDuration=null;
        try{
            arrival=new Time(formatter.parse(flightModel.getArrival()).getTime());
            departure=new Time(formatter.parse(flightModel.getDeparture()).getTime());
            haltDuration=new Time(formatter.parse(flightModel.getHaltDuration()).getTime());
        }catch(ParseException e){
            e.printStackTrace();

        }
        flightDTO.setArrival(arrival);
        flightDTO.setDeparture(departure);
        flightDTO.setHaltDuration(haltDuration);

        return flightService.save(flightDTO);

    }
}
