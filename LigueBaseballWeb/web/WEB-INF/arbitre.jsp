<%-- 
    Document   : arbitre
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
    </head>
    <body>
        <h1>Liste des arbitres</h1>
        <FORM ACTION="test" METHOD="GET">
            <%
              // calcul de la liste de prêts du membre
              Connexion ligueInterrogation = (Connexion) session.getAttribute("Connexion");
            %>
            <%
              ArbitreHandler handler = new ArbitreHandler(ligueInterrogation);
            //  int liste = handler.getAll().size();
            %>
                <table
                style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
                border="1" cellspacing="2" cellpadding="2">
                  <tbody>
                <%-- titre des colonnes --%>
                    <tr>
                    <td style="vertical-align: top;">id<br></td>
                    <td style="vertical-align: top;">nom<br></td>
                    <td style="vertical-align: top;">prenom<br></td>
                    </tr>
            <%
              ArrayList<Arbitre> list = handler.getAll();
                for (Arbitre arbitre : list)
                {
            %> 
            <tr><td><%=arbitre.id %></td><td><%=arbitre.nom %></td><td><%=arbitre.prenom %></td></tr>   
            <%       
                }
            %>
                  </tbody>
                </table>
          </FORM>
                  
          <FORM ACTION="arbitre" METHOD="POST">
                <BR>
                <BR>
                Nom de l'arbitre: <INPUT TYPE="TEXT" NAME="nom" >
                <BR>
                <BR>
                Prenom de l'arbitre : <INPUT TYPE="TEXT" NAME="prenom">
                <BR>
           <INPUT TYPE="SUBMIT" NAME="ajouter" VALUE="Ajouter">
        </FORM>
    </body>
</html>
