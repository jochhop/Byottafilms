/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Peliculas;

import Modelo.Valoraciones.Valoracion;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

/**
 *
 * @author jose
 */
@Entity(name="Peliculas")
public class Pelicula implements Serializable {    
    
    @Id
    @GeneratedValue
    private int ID;    
    private String titulo;
    private String descripcion;
    private String portada;
    private String trailer;
    @OneToMany(cascade=CascadeType.ALL)
    private ArrayList<Valoracion> valoraciones;
    
    public Pelicula(){
    }

    public Pelicula(int id, String titulo, String descripcion, String portada, String trailer, ArrayList<Valoracion> valoraciones) {        
        this.ID = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.portada = portada;
        this.trailer = trailer;
        this.valoraciones = valoraciones;
    }
    
     public Pelicula(int id, String titulo, String descripcion, String portada, String trailer) {        
        this.ID = id;
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

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }
    
    
    
}
