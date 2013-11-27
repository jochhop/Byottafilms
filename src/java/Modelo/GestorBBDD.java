/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Modelo.Peliculas.ConjuntoPeliculas;
import Modelo.Peliculas.Pelicula;
import Modelo.Usuarios.ConjuntoUsuarios;
import Modelo.Usuarios.Usuario;
import Modelo.Valoraciones.ConjuntoValoraciones;
import Modelo.Valoraciones.Valoracion;
import Persistencia.GestorPersistencia;
import java.util.ArrayList;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
public class GestorBBDD {
    
    //GESTIÓN DE PELÍCULAS
    public ConjuntoPeliculas selectPeliculas(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Pelicula");
        ConjuntoPeliculas peliculas = new ConjuntoPeliculas((ArrayList)consulta.getResultList());
        return peliculas;
    }
    
    public ConjuntoPeliculas selectIntervaloPeliculas(GestorPersistencia gp, int ini, int fin){
        Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Pelicula ORDER BY ID DESC LIMIT "+fin);
        ConjuntoPeliculas peliculas = new ConjuntoPeliculas((ArrayList)consulta.getResultList());
        return peliculas;
    }
    
    public Pelicula selectPeliculaById(GestorPersistencia gp, Pelicula idPelicula){
        return gp.getEntityManager().find(Pelicula.class, idPelicula);
    }
    
    public int getNumeroPeliculas(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT COUNT(*) FROM Pelicula");
        return (Integer) consulta.getSingleResult();
    }
    
     //GESTIÓN DE USUARIOS
    public Usuario selectUsuarioById(GestorPersistencia gp, Usuario idUsuario){
        return gp.getEntityManager().find(Usuario.class, idUsuario);
    }
    
    public ConjuntoUsuarios selectUsuarios(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Usuario");
        ConjuntoUsuarios usuarios = new ConjuntoUsuarios((ArrayList)consulta.getResultList());
        return usuarios;
    }
 
    //GESTIÓN DE VALORACIONES
    public Valoracion selectValoracionById(GestorPersistencia gp, Valoracion idValoracion){
        return gp.getEntityManager().find(Valoracion.class, idValoracion);
    }
    
    public ConjuntoValoraciones getValoracionesByPelicula(GestorPersistencia gp, int idPelicula){
        ConjuntoValoraciones valoraciones;
        Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Valoracion WHERE idPelicula = "+idPelicula);
        valoraciones = new ConjuntoValoraciones((ArrayList)consulta.getResultList());
        return valoraciones;
    }
    
    public float getNotaPelicula(GestorPersistencia gp, int idPelicula){
        ConjuntoValoraciones valoraciones;
        valoraciones = getValoracionesByPelicula(gp, idPelicula);
        float nota = 0;
        
        for(int i = 0;i<valoraciones.getListValoraciones().size();i++){
           nota = nota + (float)valoraciones.getListValoraciones().get(i).getNota();
        }
        
        return (float)nota/(float)valoraciones.getListValoraciones().size();
    }
}
