<%-- 
    Document   : head
    Created on : 26-nov-2013, 12:54:57
    Author     : jose
--%>

<%@page import="Modelo.Usuarios.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="Styles//mystyle.css" type="css" />
        <script src="http://code.jquery.com/jquery.js"></script>
        <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ByottaFilms</title>
    </head>
    <body>
    <% 
        HttpSession session_actual=request.getSession(true);
        Usuario user=(Usuario)session_actual.getAttribute("user");
        String message=(String)session_actual.getAttribute("message");
    %>
    <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">ByottaFilms</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Buscar película">
      </div>
    </form>
    <% if (user==null){ %>
        <form class="navbar-form navbar-right" method="POST" action="/Byottafilms/login">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Nick" name="usuario">
            </div>
            <div class="form-group">
            <input type="password" class="form-control" placeholder="Contraseña" name="password">
          </div>
          <button type="submit" class="btn btn-info" title="Inicia sesión">
              <span class="glyphicon glyphicon-off"></span>
          </button>
        </form>
     <%}else{%>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown" style="color:#ffffff">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=user.getNombre()+" "+user.getApellidos()%><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Mi perfil</a></li>
                    <li><a href="#">Salir</a></li>
                 </ul>
            </li>
        </ul>
     <%}%>
  </div><!-- /.navbar-collapse -->
</nav>
    <div class="container">
    <% if(message!=null){%>
        <%=message%>
    <%}%>
