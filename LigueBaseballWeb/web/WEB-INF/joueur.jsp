<%-- 
    Document   : joueur
    Created on : Apr 20, 2015, 11:26:27 AM
    Author     : Steven
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="liguebaseball.*" import="java.lang.Integer.*" import="java.util.Map" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
              <jsp:include page="header.jsp" />
        <title>Gestion baseball</title>
    </head>
    <body>
        <h1>Liste des joueurs</h1>
        <FORM ACTION="test" METHOD="GET">
            <%
              // calcul de la liste de prêts du membre
              Connexion ligueInterrogation = (Connexion) session.getAttribute("Connexion");
            %>
            <%
              JoueurHandler handler = new JoueurHandler(ligueInterrogation);
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
                    <td style="vertical-align: top;">idEquipe<br></td>
                    <td style="vertical-align: top;">Nom de l'équipe<br></td>
                    </tr>
            <%
              FaitPartieHandler faitpartieHandler = new FaitPartieHandler(ligueInterrogation);
              EquipeHandler equipeHandler = new EquipeHandler(ligueInterrogation);
              JoueurHandler joueurHandler = new JoueurHandler(ligueInterrogation);
              if ((Integer) (session.getAttribute("equipe")) == null)
              {
              Map<Integer, Integer> FaitPartie = faitpartieHandler.getAll();
              
            for (Map.Entry<Integer, Integer> entry : FaitPartie.entrySet())
            {
                int JoueurId = entry.getKey();
                int EquipeId = entry.getValue();
                Equipe eq = equipeHandler.getEquipe(EquipeId);
                Joueur j = joueurHandler.getJoueur(JoueurId);
                %>
                
                <tr><td><%=j.id %></td><td><%=j.nom %></td><td><%=j.prenom %></td><td><%=eq.id %></td><td><%=eq.nom %></td></tr>   
                <%
            }
              } else
                        {
                            
                            Equipe eq = equipeHandler.getEquipe((Integer) session.getAttribute("equipe"));
                            ArrayList<Joueur> joueurList = faitpartieHandler.getJoueursByEquipe(eq.id);
                            for (Joueur j : joueurList)
                            {
                                %>
                                <tr><td><%=j.id %></td><td><%=j.nom %></td><td><%=j.prenom %></td><td><%=eq.id %></td><td><%=eq.nom %></td></tr>
                                <%
                            }
                        }
                %>
     
                  </tbody>
                </table>
          </FORM>
                
                
                
                <FORM ACTION="joueur" METHOD="POST">
                <BR>
                <BR>
                id de l'équipe: <INPUT TYPE="TEXT" NAME="equipe" >
                <BR>
                <INPUT TYPE="SUBMIT" NAME="byEquipe" VALUE="Equipe">
        </FORM>
    </body>
</html>
