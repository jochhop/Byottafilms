/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author jose
 */
public class Pelicula {
    int ID;
    String titulo;
    String descripcion;
    String portada;
    String trailer;
    
    public Pelicula(){
    }

    public Pelicula(int ID, String titulo, String descripcion, String portada, String trailer) {
        this.ID = ID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.portada = portada;
        this.trailer = trailer;
    }

    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public String getTrailer() {
        return trailer;
    }
    
    
    
}
