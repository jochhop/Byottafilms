<%-- 
    Document   : profile
    Created on : 16-dic-2013, 16:48:24
    Author     : jose
--%>
<%@page import="Modelo.Usuarios.Usuario"%>
<% 
    HttpSession session_actual=request.getSession(true);
    Usuario user=(Usuario)session_actual.getAttribute("user");
    String message=(String)session_actual.getAttribute("message");
%>