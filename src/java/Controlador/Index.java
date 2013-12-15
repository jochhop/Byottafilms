/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Excepciones.ErrorDatoNoValido;
import Modelo.Algoritmos;
import Modelo.Cargadatos;
import Modelo.GestorBBDD;
import Modelo.ItemSim;
import Modelo.Peliculas.Pelicula;
import Modelo.Recomendacion;
import Modelo.SerializarModeloSimilitud;
import Modelo.Usuarios.ConjuntoUsuarios;
import Modelo.Usuarios.Usuario;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose
 */
public class Index extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        GestorBBDD gbb = new GestorBBDD();
       
        
        GestorPersistencia.newConexion();
        ArrayList<Pelicula> ap = new ArrayList<Pelicula>();
        ConjuntoUsuarios au = new ConjuntoUsuarios();
        Cargadatos cd = new Cargadatos(GestorPersistencia.instancia());
         
         cd.cargarPeliculas("C:\\Users\\Marci\\Documents\\NetBeansProjects\\Byottafilms\\src\\java\\Recursos\\peliculas2.csv", ap);
         cd.cargarValoraciones("C:\\Users\\Marci\\Documents\\NetBeansProjects\\Byottafilms\\src\\java\\Recursos\\ratings7.csv", ap, au);
         
         /////Inicio Pruebas Marcial 
         System.out.println("Calculando Medias");
         cd.calcularMedias(au.getListUsuarios(),ap);
         System.out.println("Medias Calculadas");
         
         ArrayList<Recomendacion> recom = new ArrayList();
         Algoritmos al = new Algoritmos();
        try {
            al.ejecucionTrainingCoseno(30, ap);
        } catch (ErrorDatoNoValido ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
         Usuario u = au.getUsuario(437);
         System.out.println(u.getValoraciones().size());
         SerializarModeloSimilitud deserializar = new SerializarModeloSimilitud();
         try {
                HashMap<Long, TreeSet<ItemSim>> modeloS = deserializar.deserializar("C:\\Users\\Marci\\Documents\\NetBeansProjects\\Byottafilms\\src\\java\\Recursos\\30-Coseno").getModeloSimilitud();
                recom = al.getRecomendaciones(u, modeloS, GestorPersistencia.instancia());
                System.out.println(recom.size());

        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        int j = 0;
        for(Recomendacion i : recom){
            System.out.println("Pelicula"+j+":" + i.getPelicula().getTitulo()+"Valoracion:" + i.getRecomendacion()+ "\n");
             /////Fin pruebas
            j++;
            request.getSession().setAttribute("gbb", gbb);
        }
        try {
            int min, max;
            if(request.getParameterNames().hasMoreElements()){
                min=Integer.parseInt(request.getParameter("min"));
                max=Integer.parseInt(request.getParameter("max"));           
            }else{
                min=0;
                max=10;
            }

            List<Pelicula> pelis=new ArrayList<Pelicula>();
            int numPelis;
            Query consulta=GestorPersistencia.instancia().getEntityManager().createQuery("SELECT p FROM Peliculas p WHERE (p.ID>"+min+" AND p.ID<="+max+")");
            pelis=consulta.getResultList();
            Query consulta2=GestorPersistencia.instancia().getEntityManager().createQuery("SELECT p FROM Peliculas p");
            numPelis=consulta2.getResultList().size();
            RequestDispatcher dispatcher = request.getRequestDispatcher("head.jsp");
            dispatcher.include(request, response);
            request.setAttribute("pelis",pelis);
            request.setAttribute("numPelis",numPelis);
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("footer.jsp");
            dispatcher.include(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
