/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.GestorBBDD;
import Modelo.Peliculas.Pelicula;
import Modelo.Usuarios.Usuario;
import Persistencia.GestorPersistencia;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose
 */
public class login extends HttpServlet {

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
            Usuario user;
            String message;
            String usuario=request.getParameter("usuario");
            String password=request.getParameter("password");
            //user=GestorBBDD.selecUsuarioByNick(GestorPersistencia.instancia(), usuario);
            HttpSession nueva_sesion = request.getSession(true);
            if(!usuario.isEmpty() && !password.isEmpty()){
                user=GestorBBDD.selectUsuarioByNick(GestorPersistencia.instancia(), usuario);
                if(user.getId()!=-1){
                    if(user.getPassword().compareTo(password)==0){
                        message="<div class=\"alert alert-success fade in\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                                + "Hola <strong>"+user.getNombre()+"</strong>! te has logueado con éxito :D</div>";
                        nueva_sesion.setAttribute("user", user);
                    }else{
                        message="<div class=\"alert alert-danger fade in\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                                + "El nick o la contraseña son incorrectos.</div>";
                        //user=null;
                    }
                    nueva_sesion.setAttribute("message", message);
                    response.sendRedirect("/Byottafilms/");
                }else{
                    message="<div class=\"alert alert-danger fade in\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                                + "El nick o la contraseña son incorrectos.</div>";
                    nueva_sesion.setAttribute("message", message);
                    response.sendRedirect("/Byottafilms/");
                }
            }else{
                message="<div class=\"alert alert-danger fade in\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>"
                                + "El nick o la contraseña son incorrectos.</div>";
                nueva_sesion.setAttribute("message", message);
                response.sendRedirect("/Byottafilms/");
            }
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
