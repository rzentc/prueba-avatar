package com.empresax.serviciorest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexion {

    private String url;
    private String metodo;
    private HttpURLConnection conexion;

    public Conexion(String url, String metodo) {
        this.url = url;
        this.metodo = metodo;
    };
    
    
    public String start() {
        String rpta = "";
        try {

            URL url = new URL(this.url);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod(this.metodo);
            conexion.setRequestProperty("Accept", "application/json");
            if (conexion.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conexion.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conexion.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                rpta += line;
            }
        } catch (Exception e) {
            return "Exception in NetClientGet: " + e;
        }
        
        return rpta;
    }
    
    public void close() {
        conexion.disconnect();
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
}
