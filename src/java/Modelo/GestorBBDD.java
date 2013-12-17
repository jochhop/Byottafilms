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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author jose
 */
public class GestorBBDD {
    
    GestorPersistencia gpersistencia = null;
    
    public GestorBBDD (){
        try{
            GestorPersistencia.newConexion();
            gpersistencia=GestorPersistencia.instancia();
        }catch(Exception e){
            System.out.println("ERROR al abrir base de datos: "+e.getMessage());
        }
    }
    
    public GestorPersistencia getGestorPersistencia(){
        return gpersistencia;
    }
    
    //GESTIÓN DE PELÍCULAS
    public static ConjuntoPeliculas selectPeliculas(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT p FROM Peliculas p");
        ConjuntoPeliculas peliculas = new ConjuntoPeliculas((ArrayList)consulta.getResultList());
        return peliculas;
    }
    
    public ConjuntoPeliculas selectIntervaloPeliculas(int ini, int fin){
        Query consulta = gpersistencia.getEntityManager().createQuery("SELECT * FROM Pelicula ORDER BY ID DESC LIMIT "+fin);
        ConjuntoPeliculas peliculas = new ConjuntoPeliculas((ArrayList)consulta.getResultList());
        return peliculas;
    }
   
    
    public static Pelicula selectPeliculaById(GestorPersistencia gp, long idPelicula){
        Pelicula peli;
        Query consulta=gp.getEntityManager().createQuery("SELECT p FROM Peliculas p WHERE p.ID="+idPelicula);
        peli=(Pelicula) consulta.getSingleResult();
        return peli;
    }
    
    public static ArrayList<Pelicula> selectPeliculasByTitulo(GestorPersistencia gp, String titulo){
        ArrayList<Pelicula> pelis=new ArrayList();
        Query consulta=gp.getEntityManager().createQuery("SELECT p FROM Peliculas p WHERE p.titulo LIKE '%"+titulo+"%'").setMaxResults(100);
        if(!consulta.getResultList().isEmpty()){
            pelis=(ArrayList<Pelicula>)consulta.getResultList();
        }
        return pelis;
    }
    
    public int getNumeroPeliculas(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT COUNT(*) FROM Pelicula");
        return (Integer) consulta.getSingleResult();
    }
    
    public static ArrayList<Pelicula> getPeliculasIntervalo(GestorPersistencia gp, int min, int max){
        ArrayList<Pelicula> pelis;
        Query consulta=gp.getEntityManager().createQuery("SELECT p FROM Peliculas p WHERE (p.ID>"+min+" AND p.ID<="+max+")");
        pelis=(ArrayList<Pelicula>)consulta.getResultList();
        return pelis;
    }
     //GESTIÓN DE USUARIOS
    public static Usuario selectUsuarioById(GestorPersistencia gp, Long idUsuario){
         Query consulta =gp.getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.ID = "+idUsuario);
         Usuario u = (Usuario)consulta.getSingleResult();
         return u;
    }
    
    public ConjuntoUsuarios selectUsuarios(GestorPersistencia gp){
        Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Usuario");
        ConjuntoUsuarios usuarios = new ConjuntoUsuarios((ArrayList)consulta.getResultList());
        return usuarios;
    }
    

    public static Usuario selecUsuarioByNick(GestorPersistencia gp, String nickuser){
        Query consulta = gp.getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.nick = :nickuser").setParameter("nickuser", nickuser);
        Usuario usuario;
        usuario=(Usuario)consulta.getSingleResult();
        
        return usuario;
    }
 
    //GESTIÓN DE VALORACIONES
    public Valoracion selectValoracionById(GestorPersistencia gp, Valoracion idValoracion){
        return gp.getEntityManager().find(Valoracion.class, idValoracion);
    }
    
    public ConjuntoValoraciones getValoracionesByPelicula(GestorPersistencia gp, int idPelicula){
        ConjuntoValoraciones valoraciones = null;
        try{
            Query consulta = gp.getEntityManager().createQuery("SELECT * FROM Valoracion WHERE idPelicula = "+idPelicula);
            valoraciones = new ConjuntoValoraciones((ArrayList)consulta.getResultList());
        }catch(Exception e){
            System.out.println("ERROR: al devolver las valoraciones de la película. "+e.getMessage());
        }
        return valoraciones;
    }
    
    public static ArrayList<Valoracion>selectValoracionesByUsuario(GestorPersistencia gp, long idUsuario){
        ArrayList<Valoracion> valoraciones;
        Query consulta=gp.getEntityManager().createQuery("SELECT v FROM Valoraciones v WHERE v.idUsuario = :idUsuario").setParameter("idUsuario", idUsuario);
        valoraciones=(ArrayList<Valoracion>)consulta.getResultList();
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
    //GESTION DE RECOMENDACIONES
    public static ArrayList<Pelicula> getRecomendaciones(Usuario usu, GestorPersistencia gp){
        SerializarModeloSimilitud ms = new SerializarModeloSimilitud();
        Algoritmos al = new Algoritmos();
        int i = 0;
        ArrayList<Recomendacion> recom_list = new ArrayList();
        ArrayList<Pelicula> recom_pelis = new ArrayList();
        System.out.println("EntraaaaaAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAaa");
        
        try {
            String url = 30+"-"+0+"-"+0;
            //SerializarModeloSimilitud deserializar = new SerializarModeloSimilitud();
            HashMap<Long, TreeSet<ItemSim>> modeloS;// = deserializar.deserializar("modelosSimilitud/"+url+".bin").getModeloSimilitud();
            System.out.println("EntraaaaaAAAAAAAAAAAAAAAAAAAA12312312312312312312312312AAAAAAAAAAAAaa");
            
            long id = 2130;
        
            //Usuario u = this.getUsuarioBD(instancia, id);
            //this.getRecomendaciones(u, 8, modeloS, instancia);
            //ms = GestorBBDD.deserializar("modelosSimilitud/30-0-0"); 
            modeloS = ms.deserializar("modelosSimilitud/30-0-0.bin").getModeloSimilitud();
            System.out.println("paaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAaa");
            System.out.println("tamaño del modelo 1: "+modeloS.size());
            recom_list = al.getRecomendaciones(usu, modeloS, gp);
            System.out.println("tamaño del modelo: "+modeloS.size());
            while(recom_list.iterator().hasNext()){
                recom_pelis.add(recom_list.get(i).getPelicula());
                i++;
            }
            
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            
        }
        return recom_pelis;
    }
    
    public static SerializarModeloSimilitud deserializar (String archivo) throws IOException, ClassNotFoundException {        
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo));
        return (SerializarModeloSimilitud) entrada.readObject();
    }
}
