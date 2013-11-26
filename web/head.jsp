<%-- 
    Document   : head
    Created on : 26-nov-2013, 12:54:57
    Author     : jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Styles/css/bootstrap.min.css" type="text/css" />
        <script src="http://code.jquery.com/jquery.js"></script>
        <script type="text/javascript" src="Styles/js/bootstrap.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <div class="navbar navbar-inverse nav-bar-fixed-top">
        <div class="navbar-inner">
          <div class="container">

            <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </a>

            <!-- Be sure to leave the brand out there if you want it shown -->
            <a class="brand" href="/Byottafilms/index">ByottaFilms</a>

            <!-- Everything you want hidden at 940px or less, place within here -->
            <div class="nav-collapse collapse">
              <!-- .nav, .navbar-search, .navbar-form, etc -->
            </div>
            <form class="navbar-search pull-left">
                <input type="text" class="search-query" placeholder="Buscar película">
            </form>
            
            <form class="navbar-form pull-right">
                <input type="text" class="span2" name="nick" placeholder="nick">
                <input type="password" class="span2" name="password" placeholder="contraseña">
                <button type="submit" class="btn btn-info">Iniciar sesión</button>
            </form>
          </div>
        </div>
    </div>
