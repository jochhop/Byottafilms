/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ConjuntoValoraciones {
    
    ArrayList<Valoracion> listValoraciones;

    public ConjuntoValoraciones() {
    }
    
    public void addValoracion(int idUser, int idPelicula, float nota){
        Valoracion valoracion = new Valoracion(nota, idUser, idPelicula);
        listValoraciones.add(valoracion);
    }
    
    public int getNumeroValoraciones(){
        return listValoraciones.size();
    }
    
}
