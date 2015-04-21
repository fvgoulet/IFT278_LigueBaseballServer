<%-- 
    Document   : joueur
    Created on : Apr 20, 2015, 11:26:27 AM
    Author     : Steven
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="liguebaseball.*" import="java.lang.Integer.*" import="java.util.Map" import="java.util.ArrayList" import="liguebaseball.JoueurInTeam"%>
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
            <div class="CSSTableGenerator" >
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
             ArrayList<JoueurInTeam> FaitPartie = faitpartieHandler.getAllJoueursInTeamLeftJoin();
              
            for (JoueurInTeam entry : FaitPartie)
            {
                
                Equipe eq = equipeHandler.getEquipe(entry.equipeid);
                Joueur j = joueurHandler.getJoueur(entry.id);
                %>
                
                <tr><td><%=j.id %></td><td><%=j.nom %></td><td><%=j.prenom %></td>
                    <% if (eq != null)
                    {%>
                    <td><%=eq.id %></td><td><%=eq.nom %></td>
                 <% 
                    }%>
                </tr>   
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
                </duv>
                </table>
          </FORM>
                
                
                
                <FORM ACTION="joueur" METHOD="POST">
                <BR>
                <BR>
                id de l'équipe: <INPUT TYPE="TEXT" NAME="equipe" >
                <BR>
                <INPUT TYPE="SUBMIT" NAME="byEquipe" VALUE="Equipe">
        </FORM>
                
                 <FORM ACTION="joueur" METHOD="POST">
                <BR>
                <BR>
                Nom du joueur : <INPUT TYPE="TEXT" NAME="nom" >
                <BR>
                <BR>
                Prenom du joueur : <INPUT TYPE="TEXT" NAME="prenom">
                <BR>
           <INPUT TYPE="SUBMIT" NAME="ajouter" VALUE="Ajouter">
        </FORM>
                
                <FORM ACTION="joueur" METHOD="POST">
                <BR>
                <BR>
                Nom du joueur : <INPUT TYPE="TEXT" NAME="nom" >
                <BR>
                <BR>
                Prenom du joueur : <INPUT TYPE="TEXT" NAME="prenom">
                <BR>
           <INPUT TYPE="SUBMIT" NAME="supprimer" VALUE="Supprimer">
        </FORM>
    </body>
</html>
