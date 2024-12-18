package com.cairouniv.fci.travel_agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel-agency")
public class TravelAgencyController {

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<String> travelAgency(){
        //TODO implementation ->
        return new ResponseEntity<>("HELLO",HttpStatus.OK);
    }
}
