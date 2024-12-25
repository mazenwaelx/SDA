package com.cairouniv.fci.travel.agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TravelAgencyApplication {

    public static void main(String[] args) {
        try {

            SpringApplication.run(TravelAgencyApplication.class, args);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
