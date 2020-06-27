package com.empresax.serviciorest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.*;

@RestController
public class CineController {

    private static final String URL_SERVICIO_FILMS = "https://swapi.dev/api/films/";
    private static final String URL_SERVICIO_ACTORES = "https://swapi.dev/api/people/%s/";

    @CrossOrigin
    @GetMapping("/peliculas")
    public FilmsService obtenerPeliculas()  {
	    
        ObjectMapper mapper = new ObjectMapper();
        Conexion conexion = new Conexion(URL_SERVICIO_FILMS, "GET");
        try {
            FilmsService filmService =  mapper.readValue(
                conexion.start(), 
                FilmsService.class
            );
            conexion.close();
            return filmService;
        } catch(Exception e) {
            conexion.close();
            return new FilmsService();
        }
    }
    
    @CrossOrigin
    @GetMapping("/actores")
    public ConjuntoActores obtenerActores(@RequestParam(value = "ids") String ids)  {
	    
        ObjectMapper mapper = new ObjectMapper();
        Conexion conexion = new Conexion(null,"GET");
        try {
            ConjuntoActores conjunto = new ConjuntoActores();
            String[] idActores = ids.split("-");
            
           
            
           for (int i=0; i<idActores.length; ++i) {
                conexion.setUrl(String.format(URL_SERVICIO_ACTORES, idActores[i]));
                PeopleService peopleService =  mapper.readValue(
                    conexion.start(),
                    PeopleService.class
                );
                conjunto.actores.add(peopleService.name);
            }
            conexion.close();
            return conjunto;
        } catch(Exception e) {
            conexion.close();
            return new ConjuntoActores();
        }
    }
}
