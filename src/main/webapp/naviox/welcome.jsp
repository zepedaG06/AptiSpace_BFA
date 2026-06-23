<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.openxava.web.servlets.Servlets" %>
<%@ page import="org.openxava.web.style.XavaStyle" %>
<%@ page import="org.openxava.util.XavaPreferences" %>
<%
Servlets.setCharacterEncoding(request, response);
String oxVersion = org.openxava.controller.ModuleManager.getVersion();
%>
<!DOCTYPE html>
<head>
    <title>AptiSpace BFA</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<%= request.getContextPath() %>/xava/style/<%= XavaPreferences.getInstance().getStyleCSS() %>?ox=<%= oxVersion %>" rel="stylesheet" type="text/css">
    <style>
        .aptispace-welcome {
            max-width: 840px;
            margin: 8vh auto 0;
            padding: 0 24px;
            text-align: center;
        }
        .aptispace-welcome h1 {
            margin: 0 0 12px;
            font-size: 38px;
            font-weight: 700;
        }
        .aptispace-welcome h2 {
            margin: 0 0 22px;
            font-size: 18px;
            font-weight: 500;
            color: #4b5563;
        }
        .aptispace-welcome p {
            margin: 0 auto 28px;
            max-width: 760px;
            color: #374151;
            font-size: 16px;
            line-height: 1.6;
        }
        .aptispace-welcome .ox-bottom-buttons {
            margin-top: 8px;
        }
    </style>
</head>
<body id="welcome" <%= XavaStyle.getBodyClass(request) %>>
    <main class="aptispace-welcome">
        <h1>AptiSpace BFA</h1>
        <h2>Sistema de Automatización de la Batería Factorial de Aptitudes (BFA)</h2>
        <p>AptiSpace BFA es una plataforma desarrollada para administrar y automatizar la aplicación de la Prueba Espacial de Desplazamiento de la Batería Factorial de Aptitudes. El sistema permite gestionar usuarios, evaluados, grupos de evaluación, aplicaciones de pruebas, plantillas de corrección, resultados y observaciones psicológicas de forma organizada y segura.</p>
        <div class="ox-bottom-buttons">
            <a href="m/SignIn"><input type="button" tabindex="1" value="Iniciar sesión"></a>
        </div>
    </main>
</body>
