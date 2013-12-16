/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Modelo.Peliculas.Pelicula;

/**
 *
 * @author jose
 */
public class Recomendacion {
    private Pelicula pelicula;
    private double recomendacion;

    public Recomendacion(Pelicula pelicula, double recomendacion) {
        this.pelicula = new Pelicula(pelicula);
        this.recomendacion = recomendacion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public double getRecomendacion() {
        return recomendacion;
    } 
}
