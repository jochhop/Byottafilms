/*
* To change this template, choose Tools | Templates
* and open the template in the editor
*/

package Persistencia;


import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import com.mysql.jdbc.*;

/**
 *
 * @author Gabriel
 */

public class GestorPersistencia {
    
    EntityManagerFactory emf;
    EntityManager em;
    static GestorPersistencia instancia = null;
    
    public GestorPersistencia() {
        emf = Persistence.createEntityManagerFactory("ByottafilmsPU");
        em = emf.createEntityManager();
    }
    
    public static void newConexion(){
        if (instancia == null) {
            instancia = new GestorPersistencia();
        }
    }
    
    public EntityManager getEntityManager() {
        return em;
    }
           
    public static GestorPersistencia instancia() {
        return instancia;
    }
    
    public static void desconectar() {
        if (instancia != null) {
            instancia.em.getTransaction().begin();
            instancia.em.createNativeQuery("shutdown").executeUpdate();
            instancia.em.getTransaction().commit();
            instancia.em.close();
            instancia.emf.close();
            instancia = null;
        }
    }
    
}
