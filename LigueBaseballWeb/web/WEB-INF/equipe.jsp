<%-- 
    Document   : equipe
    Created on : Apr 20, 2015, 11:26:27 AM
    Author     : Steven
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="liguebaseball.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp" />
        <style type="text/css">
    <%@ include file="/WEB-INF/newcss.css" %>
</style> 
    </head>
    <body>
        <h1>Liste des equipes</h1>
        <FORM ACTION="equipe" METHOD="GET">
            <%
              // calcul de la liste de prêts du membre
              Connexion ligueInterrogation = (Connexion) session.getAttribute("Connexion");
            %>
            <%
              EquipeHandler handler = new EquipeHandler(ligueInterrogation);
            //  int liste = handler.getAll().size();
            %>
            <div class="CSSTableGenerator" >
                <table
                style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
                border="1" cellspacing="2" cellpadding="2">
                  <tbody>
                <%-- titre des colonnes --%>
                    <tr>
                    <td style="vertical-align: top;">id<br></td>
                    <td style="vertical-align: top;">nom<br></td>
                    </tr>
            <%
              ArrayList<Equipe> list = handler.getAll();
                for (Equipe equipe : list)
                {
            %> 
            <tr><td><%=equipe.id %></td><td><%=equipe.nom %></td><td>Supprimer <input type="submit" name="supprimer" value="<%=equipe.id%>"/> </td></tr>   
            <%       
                }
            %>
                  </tbody>
                </table>
            </div>
          </FORM>
          <FORM ACTION="equipe" METHOD="POST">
                <BR>
                <BR>
                Nom de l'equipe: <INPUT TYPE="TEXT" NAME="nom" >
                <BR>
                <BR>
                nom du terrain: <INPUT TYPE="TEXT" NAME="terrain">
                <BR>
           <INPUT TYPE="SUBMIT" NAME="ajouter" VALUE="Ajouter">
        </FORM>
                  
 
                 
                  
                  
                  
    </body>
</html>
