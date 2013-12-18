/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.GestorBBDD;
import Modelo.Peliculas.Pelicula;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose
 */
public class insertarpeli extends HttpServlet {

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
        try {
            HttpSession sesion_actual = request.getSession(true);
            String message;
            String titulo=request.getParameter("titulo");
            String anio=request.getParameter("anio");
            Double media;
            if(!request.getParameter("media").isEmpty()){
                media=Double.parseDouble(request.getParameter("media"));
            }else{
                media=-1.0;
            }
            String portada=request.getParameter("portada");
            String triler=request.getParameter("trailer");
            String descripcion=request.getParameter("descripcion");
            
            if(!titulo.isEmpty() && !anio.isEmpty()){
                long id=GestorBBDD.selectMaxIdPelicula(GestorPersistencia.instancia())+1;
                System.out.println("Esta es la id: "+id);
                Pelicula peli = new Pelicula(id, Integer.parseInt(anio), titulo, media);
                GestorPersistencia.instancia().getEntityManager().getTransaction().begin();
                GestorPersistencia.instancia().getEntityManager().persist(peli);
                GestorPersistencia.instancia().getEntityManager().getTransaction().commit();
                message="<div class=\"alert alert-success fade in\">"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                            + "Película insertada correctamente.</div>";
            }else{
                message="<div class=\"alert alert-danger fade in\">"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                            + "Película no insertada, el título y el año son obligatorios.</div>";
            }
            
            sesion_actual.setAttribute("message", message);
            response.sendRedirect("/Byottafilms/admin");
            
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
