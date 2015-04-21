package liguebaseballServlet;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import liguebaseball.*;

/**
 * Classe traitant les rêquetes provenant de la page arbitre.jsp
 * <P>
 * Système de gestion de liguebaseball Université de Sherbrooke
 */
public class arbitre extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("ajouter") != null)
        {
            traiterAjouter(request, response);
        }
        else if(request.getParameter("GoArbitre") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/src/arbitre.jsp");
            dispatcher.forward(request, response);
        }
        else if(request.getParameter("GoEquipe") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/src/equipe.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void traiterAjouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            if (request.getParameter("nom") == null)
            {
                throw new Exception("Aucun nom entrée");
            }
            String nom = request.getParameter("nom");
            if (request.getParameter("prenom") == null)
            {
                throw new Exception("Aucun prnom entrée");
            }
            String prenom = request.getParameter("prenom");

            Connexion ligueUpdate = (Connexion) request.getSession().getAttribute("Connexion");
			// exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (ligueUpdate)
            {
                ArbitreHandler arbitreH = new ArbitreHandler(ligueUpdate);
                arbitreH.inserer(arbitreH.getLastID() + 1, nom, prenom);
                ligueUpdate.commit();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/arbitre.jsp");
            dispatcher.forward(request, response);
            /*} catch (Exception e) {
             List listeMessageErreur = new LinkedList();
             listeMessageErreur.add(e.toString());
             request.setAttribute("listeMessageErreur", listeMessageErreur);
             RequestDispatcher dispatcher = request
             .getRequestDispatcher("/WEB-INF/arbitre.jsp");
             dispatcher.forward(request, response);*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
