package liguebaseballServlet;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import liguebaseball.*;

/**
 * Classe traitant la requête provenant de la page listePretMembre.jsp
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */
public class equipe extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("ajouter") != null)
        {
            traiterAjouter(request, response);
        }
   else if (request.getParameter("supprimer") != null)
        {
            traiterSupprimer(request, response);
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
            if (request.getParameter("terrain") == null)
            {
                throw new Exception("Aucun terrain entrée");
            }
            Connexion ligueUpdate = (Connexion) request.getSession().getAttribute("Connexion");
            String terrain = request.getParameter("terrain");
            TerrainHandler terrainH = new TerrainHandler(ligueUpdate);
            int id = terrainH.getTerrain(terrain).id;

           
			// exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (ligueUpdate)
            {
     
                EquipeHandler equipeH = new EquipeHandler(ligueUpdate);
                 equipeH.inserer(equipeH.getLastID() + 1,id, nom);
                 ligueUpdate.commit();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/equipe.jsp");
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

      public void traiterSupprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            if (request.getParameter("nom") == null)
            {
                throw new Exception("Aucun nom entrée");
            }
            String nom = request.getParameter("nom");
            Connexion ligueUpdate = (Connexion) request.getSession().getAttribute("Connexion");


           
			// exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (ligueUpdate)
            {
     
                EquipeHandler equipeH = new EquipeHandler(ligueUpdate);
                int id = equipeH.getEquipe(nom).id;
                 equipeH.supprimer(id);
                 ligueUpdate.commit();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/equipe.jsp");
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

    public void traiterRenouveler(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        /*	try {
         if (request.getParameter("pretSelectionne") == null)
         throw new BiblioException("Aucun prêt sélectionné");
         int idLivre = Integer.parseInt(request
         .getParameter("pretSelectionne"));
         String datePret = (new Date(System.currentTimeMillis())).toString();
         GestionBibliotheque biblioUpdate = (GestionBibliotheque) request
         .getSession().getAttribute("biblioUpdate");
         synchronized (biblioUpdate) {
         biblioUpdate.gestionPret.renouveler(idLivre, datePret);
         }
         RequestDispatcher dispatcher = request
         .getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
         dispatcher.forward(request, response);
         } catch (BiblioException e) {
         List listeMessageErreur = new LinkedList();
         listeMessageErreur.add(e.toString());
         request.setAttribute("listeMessageErreur", listeMessageErreur);
         RequestDispatcher dispatcher = request
         .getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
         dispatcher.forward(request, response);
         } catch (Exception e) {
         e.printStackTrace();
         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
         .toString());
         }*/
    }

    public void traiterEmprunter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/emprunt.jsp");
        dispatcher.forward(request, response);
    }

    public void traiterSelectionMembre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
