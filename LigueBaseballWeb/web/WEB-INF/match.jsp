<%-- 
    Document   : match
    Created on : Apr 20, 2015, 11:26:27 AM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="liguebaseball.*" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <h1>Liste des matchs</h1>
           
                <FORM ACTION="match" METHOD="POST">
                <BR>
                <BR>
                nom de l'Équipe: <INPUT TYPE="TEXT" NAME="equipe" >
                <BR>
                <INPUT TYPE="SUBMIT" NAME="byEquipe" VALUE="Trier">
                
                
                <FORM ACTION="match" METHOD="POST">
                <BR>
                <BR>
                Date: <INPUT TYPE="TEXT" NAME="date" >
                <BR>
                <INPUT TYPE="SUBMIT" NAME="byDate" VALUE="Date">
                
        <FORM ACTION="test" METHOD="GET">
            <%
              // calcul de la liste de prêts du membre
              Connexion ligueInterrogation = (Connexion) session.getAttribute("Connexion");
            %>
            <%
              MatchHandler handler = new MatchHandler(ligueInterrogation);
              ArbitrerHandler arbitrerHandler = new ArbitrerHandler(ligueInterrogation);
            //  int liste = handler.getAll().size();
            %>
                <table
                style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
                border="1" cellspacing="2" cellpadding="2">
                  <tbody>
                <%-- titre des colonnes --%>
                   <tr>
                    <td style="vertical-align: top;">matchid<br></td>
                    <td style="vertical-align: top;">equipelocal<br></td>
                        <td style="vertical-align: top;">equipevisiteur<br></td>
                    <td style="vertical-align: top;">terrainid<br></td>
                    <td style="vertical-align: top;">matchdate<br></td>
                    <td style="vertical-align: top;">matchheure<br></td>
                    <td style="vertical-align: top;">pointslocal<br></td>
                    <td style="vertical-align: top;">pointsvisiteur<br></td>
                    <td style="vertical-align: top;">arbitre1<br></td>
                    <td style="vertical-align: top;">arbitre2<br></td>
                    <td style="vertical-align: top;">arbitre3<br></td>
                    <td style="vertical-align: top;">arbitre4<br></td>
                    </tr>
            <%
               if ((session.getAttribute("equipe")) != null)
               {
                   EquipeHandler equipeHandler = new EquipeHandler(ligueInterrogation);
                   Equipe e = equipeHandler.getEquipe((String) session.getAttribute("equipe"));
                   ArrayList<Match> list = handler.getMatchesByEquipe(e.id);
                for (Match match : list)
                {
                    ArrayList<Arbitre> arbitreList = arbitrerHandler.getArbitresByMatch(match.id);
            %> 
            <tr><td><%=match.id %></td><td><%=match.equipelocal %></td><td><%=match.equipevisiteur %></td><td><%=match.terrainid %></td><td><%=match.date %></td><td><%=match.heure %></td><td><%=match.pointslocal %></td><td><%=match.pointsvisiteur %></td>
            
             <% for (Arbitre arbitre: arbitreList)
                {
                    %>
                    <td><%=arbitre.nom%></td>
                    <%
                }
            %>
            
            </tr>   
            <%       
                }
                session.setAttribute("equipe",null);
               }else if ((session.getAttribute("date")) != null)
                   {
            %>
            
             <%
                ArrayList<Match> matches = handler.getMatchesByDate(DateTimeHelper.convertirDate((String) session.getAttribute("date")));
            
                for (Match match : matches)
                {
                    ArrayList<Arbitre> arbitreList = arbitrerHandler.getArbitresByMatch(match.id);
            %> 
            <tr><td><%=match.id %></td><td><%=match.equipelocal %></td><td><%=match.equipevisiteur %></td><td><%=match.terrainid %></td><td><%=match.date %></td><td><%=match.heure %></td><td><%=match.pointslocal %></td><td><%=match.pointsvisiteur %></td>
             <%               for (Arbitre arbitre: arbitreList)
                {
                    %>
                    <td><%=arbitre.nom%></td>
                    <%
                }
            %>
            
            </tr>   
            <%       
                }
                session.setAttribute("date",null);
               }else if ((session.getAttribute("date")) == null)
               {
                   session.setAttribute("date","1000-10-10");
                ArrayList<Match> matches = handler.getMatchesByDate(DateTimeHelper.convertirDate((String) session.getAttribute("date")));
                for (Match match : matches)
                {
                       ArrayList<Arbitre> arbitreList = arbitrerHandler.getArbitresByMatch(match.id);
            %> 
            <tr><td><%=match.id %></td><td><%=match.equipelocal %></td><td><%=match.equipevisiteur %></td><td><%=match.terrainid %></td><td><%=match.date %></td><td><%=match.heure %></td><td><%=match.pointslocal %></td><td><%=match.pointsvisiteur %></td>
 <%               for (Arbitre arbitre: arbitreList)
                {
                    %>
                    <td><%=arbitre.nom%></td>
                    <%
                }
            %>
            
            </tr>   
            <%       
                }
               }
               
            %>
                  </tbody>
                </table>
          </FORM>
                  
    </body>
</html>
