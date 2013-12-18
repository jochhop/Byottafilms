/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.GestorBBDD;

import Modelo.Peliculas.Pelicula;
import Modelo.Recomendacion;
import Modelo.Usuarios.Usuario;
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
import javax.servlet.http.HttpSession;

public class recomendacion extends HttpServlet {

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

   
       //GestorPersistencia.newConexion();
       try {
            HttpSession sesion_actual = request.getSession(true);
            Usuario user=(Usuario)sesion_actual.getAttribute("user");
            ArrayList<Pelicula> pelis = (ArrayList<Pelicula>)sesion_actual.getAttribute("pelis2");
            int min;
            int max;

            if((request.getParameter("min")!=null) && (request.getParameter("max")!=null)){
                min = Integer.parseInt(request.getParameter("min"));
                max = Integer.parseInt(request.getParameter("max"));
            }else{
                min=0;
                max=10; 
            }
            
            if(pelis==null){
                pelis=new ArrayList();
                pelis = GestorBBDD.getRecomendaciones(user,GestorPersistencia.instancia());            
            }

            System.out.println("Tamaño de las recomendacionesasssssssssssssssssssSAAAAAAAA: "+pelis.size());
            sesion_actual.setAttribute("pelis2", pelis);
            sesion_actual.setAttribute("min", min);
            sesion_actual.setAttribute("max", max);
            RequestDispatcher dispatcher = request.getRequestDispatcher("head.jsp");
            dispatcher.include(request, response);
            dispatcher = request.getRequestDispatcher("recomendacion.jsp");
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
