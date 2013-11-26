/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Valoraciones;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ConjuntoValoraciones {
    
    ArrayList<Valoracion> listValoraciones;

    public ConjuntoValoraciones() {
        listValoraciones = new ArrayList();
    }
    
    public ConjuntoValoraciones(ArrayList<Valoracion> lValoraciones) {
        listValoraciones = new ArrayList<Valoracion>();
        listValoraciones = lValoraciones;
    }
    
    public void addValoracion(int idUser, int idPelicula, float nota){
        Valoracion valoracion = new Valoracion(nota, idUser, idPelicula);
        listValoraciones.add(valoracion);
    }
    
    public int getNumeroValoraciones(){
        return listValoraciones.size();
    }
    
    public ArrayList<Valoracion> getListValoraciones(){
        return listValoraciones;
    }
    
}
