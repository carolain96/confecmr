package com.sunedu.api.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunedu.api.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.*;
@RestController
@RequestMapping(value = "api/dashboard", produces = "application/json")
public class DashboardController {
    private final ClienteRepository egresadoData;

    public DashboardController(ClienteRepository egresadoData){
        this.egresadoData = egresadoData;
    } 
    /*@GetMapping(value = "/montototal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> productos(){
        return  new ResponseEntity<List<Map<String, Object>>>(
            egresadoData.querySumaTotalByMonth(), HttpStatus.OK);
    }*/
    @GetMapping(value = "/montototalinscripciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> inscripcionesdni(){
        return  new ResponseEntity<List<Map<String, Object>>>(
            egresadoData.querySumaTotalByReceptor(), HttpStatus.OK);
    }
    @GetMapping(value = "/totalinscripciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Object>>> inscripciones(){
        return  new ResponseEntity<List<Map<String, Object>>>(
            egresadoData.querySumaTotalByMonthInscripciones(), HttpStatus.OK);
    }
}
