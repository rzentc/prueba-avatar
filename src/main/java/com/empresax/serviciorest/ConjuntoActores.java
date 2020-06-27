package com.empresax.serviciorest;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConjuntoActores {
    public List<String> actores = new ArrayList<String>();
}
