<%-- 
    Document   : profile
    Created on : 16-dic-2013, 16:48:24
    Author     : jose
--%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%@page import="Modelo.Usuarios.Usuario"%>
<% 
    HttpSession session_actual=request.getSession(true);
    Usuario user=(Usuario)session_actual.getAttribute("user");
%>

<h1 class="center"><%=user.getNombre()+" "+user.getApellidos()%></h1>
<div class="row">
    <div class="col-md-3">
        <img src="http://www.theonlinedater.com/wp-content/uploads/2010/03/say-cheese-please2.png" class="img-thumbnail">
        <!-- Button trigger modal -->
        <a href="/Byottafilms/misvaloraciones" class="btn btn-primary btn-lg btn-block">
          Mis valoraciones
        </a>
    </div>
        <div class="col-md-7">
            <table class="table">
                <tr>
                   <td style="font-weight: bold">Email</td>
                   <td><%=user.getEmail()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Nick</td>
                   <td><%=user.getNick()%></td>
                </tr>
                <tr>
                   <td style="font-weight: bold">Rango</td>
                   <% if(user.getRol()>0){ %>
                        <td><h4><span class="label label-warning">Administrador</span></h4></td>
                   <%}else{%>
                        <td><h4><span class="label label-default">Usuario</span></h4></td>
                   <%}%>
                </tr>
            </table>
        </div>
    </div>

