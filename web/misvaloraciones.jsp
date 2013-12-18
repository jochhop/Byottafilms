<%-- 
    Document   : misvaloraciones
    Created on : 16-dic-2013, 17:59:12
    Author     : jose
--%>
<%@page import="Modelo.Usuarios.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%
    HttpSession sesion_actual = request.getSession(true);
    Usuario user=(Usuario)sesion_actual.getAttribute("user");
    ArrayList<Pelicula> pelis=(ArrayList<Pelicula>)sesion_actual.getAttribute("pelis");
    int min = (Integer)sesion_actual.getAttribute("min");
    int max = (Integer)sesion_actual.getAttribute("max");
%>
<h2>Tus valoraciones <%=user.getNombre()%></h2>
<table class="table table-striped">
    <thead>
        <tr>
        <th>#</th>
        <th>Año</th>
        <th>Título</th>
        <th>Nota media</th>
        <th>Opciones</th>
        </tr>
    </thead>
    <tbody>
        <% 
            if(max>pelis.size()){
                max=pelis.size();
            }
            for(int i=min;i<max;i++){ 
        %>
        <tr>
            <td><%= pelis.get(i).getID() %></td>
            <td><%= pelis.get(i).getAnio() %></td>
            <td><%= pelis.get(i).getTitulo() %></td>
            <td><%= String.format("%.2f", pelis.get(i).getMedia()) %></td>
            <td><a title="Ver detalles" href=<%="/Byottafilms/pelicula?id="+pelis.get(i).getID()%>><span class="glyphicon glyphicon-eye-open"></span></td>
        </tr>
        <% } %>
      </tbody>
</table>
<ul class="pager">
    <% if(min>1) {%>
        <li><a href=<%="/Byottafilms/misvaloraciones?min="+(min-10)+"&max="+(max-10)%>>Anterior</a></li>
    <%}%>
    <li><a href=<%="/Byottafilms/misvaloraciones?min="+(min+10)+"&max="+(max+10)%>>Siguiente</a></li>
</ul>
