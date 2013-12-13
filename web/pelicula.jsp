<%-- 
    Document   : pelicula
    Created on : 13-dic-2013, 16:02:17
    Author     : jose
--%>
<%@page import="Controlador.getInfoPeli"%>
<%@page import="Modelo.Peliculas.Pelicula"%>
<%Pelicula peli=(Pelicula)request.getAttribute("peli");
  getInfoPeli info=(getInfoPeli)request.getAttribute("info");%>
<h1><%=peli.getTitulo()%></h1>
<div class="row">
    <div class="col-md-4"><img style="width: 250px;" src=<%=info.getPoster()%>></img></div>
    <div class="col-md-4"><iframe width="640" height="390" src=<%=info.getTrailer()%> frameborder="0"></iframe></div>
</div>
