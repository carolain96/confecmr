package com.sunedu.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.*;

import java.math.BigInteger;
import java.util.Optional;
import com.sunedu.api.model.Cliente;
import com.sunedu.api.repositories.*;


@RestController
@RequestMapping(value = "api/Cliente",produces = "application/json")
public class ClienteController {
    
    private final ClienteRepository egresadoData;
  
    public ClienteController(ClienteRepository egresadoData){
        
        this.egresadoData = egresadoData;

    }
      @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<BigInteger> create(@RequestBody Cliente e){
          egresadoData.save(e);
          egresadoData.flush(); 
         
          return new ResponseEntity<BigInteger>(e.getDni(), HttpStatus.CREATED);
      }
  
      @GetMapping(value = "/{Dni}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Cliente> findByDni(@PathVariable BigInteger Dni){
          Optional<Cliente> optEgresado =egresadoData.findByDni(Dni);
          if(optEgresado.isPresent()){
            Cliente egresado = optEgresado.get();
              return new ResponseEntity<Cliente>(egresado,HttpStatus.OK);
          }else{
              return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
          }   
      }
}
