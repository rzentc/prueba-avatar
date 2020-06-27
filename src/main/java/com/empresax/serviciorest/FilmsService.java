package com.empresax.serviciorest;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmsService {

    public int count;
    public Object next;
    public Object previus;
    public List<Film> results;

    public static class Film {
        public List<String> characters;
        public String created;
        public String director;
        public String edited;
        public int episode_id;
        public String opening_crawl;
        public List<String> planets;
        public String producer;
        public String release_date;
        public List<String> species;
        public List<String> starships;
        public String title;
        public String url;
        public List<String> vehicles;  
    }
}
