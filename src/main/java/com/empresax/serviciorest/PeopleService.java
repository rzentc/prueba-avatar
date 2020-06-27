package com.empresax.serviciorest;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleService {

    public String birth_year;
    public String created;
    public String edited;
    public String eye_color;
    public List<String> films;
    public String gender;
    public String hair_color;
    public String height;
    public String homeworld;
    public String mass;
    public String name;
    public String skin_color;
    public List<String> species;
    public List<String> starships;
    public String url;
    public List<String> vehicles;  
}
