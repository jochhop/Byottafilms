/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
public class Valoracion {
    float nota;
    GregorianCalendar fecha;

    public Valoracion(){}
    
    public Valoracion(float nota, GregorianCalendar fecha) {
        this.nota = nota;
        this.fecha = fecha;
    }

    public float getNota() {
        return nota;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }
    
    
    
}
