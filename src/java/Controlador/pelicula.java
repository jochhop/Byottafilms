/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Peliculas.Pelicula;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class pelicula extends HttpServlet {

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
            Pelicula peli=new Pelicula();
            Query consulta=GestorPersistencia.instancia().getEntityManager().createQuery("SELECT p FROM Peliculas p WHERE p.ID="+request.getParameter("id"));
            peli=(Pelicula) consulta.getSingleResult();
            System.out.println("Titulo de la peli: "+peli.getTitulo());
            getInfoPeli info = new getInfoPeli(peli.getTitulo());
            if(info.getDescripcion().isEmpty()){
                info.setDescripcion("No hay descripción disponible para esta película.");
            }
            if(info.getPoster().isEmpty()){
                info.setPoster("http://www.51allout.co.uk/wp-content/uploads/2012/02/Image-not-found.gif");
            }
            if(info.getTrailer().isEmpty()){
                info.setTrailer("No hay trailer disponible para esta película.");
            }else{
                info.setTrailer("<iframe width=\"540\" height=\"290\" src=\"//www.youtube.com/embed/"+info.getTrailer()+"\" frameborder=\"0\" allowfullscreen></iframe>");
            }
            if(info.getActores().isEmpty()){
                info.setActores("No hay información sobre los actores en esta película.");
            }
            if(info.getEstilo().isEmpty()){
                info.setEstilo("No hay información sobre el género de esta película.");
            }
            if(info.getDirector().isEmpty()){
                info.setDirector("No hay información sobre el director de esta película.");
            }
            if(info.getGuionista().isEmpty()){
                info.setGuionista("No hay información sobre el guionista de esta película.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("head.jsp");
            dispatcher.include(request, response);
            request.setAttribute("peli",peli);
            request.setAttribute("info",info);
            dispatcher = request.getRequestDispatcher("pelicula.jsp");
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
