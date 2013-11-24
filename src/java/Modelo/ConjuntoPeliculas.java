/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.ArrayList;

public class ConjuntoPeliculas {
    
    ArrayList<Pelicula> listPeliculas;

    public ConjuntoPeliculas() {
    }  
    
    public ConjuntoPeliculas(ArrayList<Pelicula> listPeliculas) {
        this.listPeliculas = listPeliculas;
    }

    public ArrayList<Pelicula> getListPeliculas() {
        return listPeliculas;
    }
    
    public Pelicula getPelicula(int id){                        
        return listPeliculas.get(id);
    }
        
    public void newPelicula(int id, String titulo, String descripcion, String portada, String trailer){
        
        Pelicula pelicula = new Pelicula(id, titulo, descripcion, portada, trailer, null);
        
        listPeliculas.add(pelicula);        
        
    }
    
    public void deletePelicula(int ID){
         if(listPeliculas.remove(listPeliculas.get(ID))){
            
        }else{
            //EL fallo copon.
        }
    }
    
    
}
