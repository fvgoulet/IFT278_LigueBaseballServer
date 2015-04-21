/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liguebaseballServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import liguebaseball.ArbitreHandler;
import liguebaseball.Connexion;
import liguebaseball.Joueur;
import liguebaseball.JoueurHandler;

/**
 * Classe traitant les rêquetes provenant de la page joueur.jsp
 * <P>
 * Système de gestion de liguebaseball Université de Sherbrooke
 */
public class joueur extends HttpServlet
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
        } else if (request.getParameter("supprimer") != null)
        {
            traiterSupprimer(request, response);
        }
            else if (request.getParameter("byEquipe") != null)
        {
            traiterByEquipe(request, response);
        }

    }

     /**
     * Ajout d'un joueur
     *
     * @author steven
     */
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
                throw new Exception("Aucun pernom entrée");
            }
            String prenom = request.getParameter("prenom");

            Connexion ligueUpdate = (Connexion) request.getSession().getAttribute("Connexion");
            
            // exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (ligueUpdate)
            {
                JoueurHandler joueurH = new JoueurHandler(ligueUpdate);
                joueurH.inserer(joueurH.getLastID() + 1, nom, prenom);
                ligueUpdate.commit();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueur.jsp");
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
    /**
     * Supprimer un joueur
     *
     * @author steven
     */
    public void traiterSupprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
                throw new Exception("Aucun pernom entrée");
            }
            String prenom = request.getParameter("prenom");

            Connexion ligueUpdate = (Connexion) request.getSession().getAttribute("Connexion");
            
            // exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (ligueUpdate)
            {
                JoueurHandler joueurH = new JoueurHandler(ligueUpdate);
                int id = joueurH.getJoueurId(nom, prenom).id;
                joueurH.supprimer(id);
                ligueUpdate.commit();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueur.jsp");
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
    
     /**
     * trier par equipe
     *
     * @author steven
     */
    public void traiterByEquipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
         if (request.getParameter("equipe") == null)
               throw new Exception("Aucune équipe entréee");
         int idEquipe = Integer.parseInt(request
         .getParameter("equipe"));
         request.getSession().setAttribute("equipe", idEquipe);
         RequestDispatcher dispatcher = request
         .getRequestDispatcher("/WEB-INF/joueur.jsp");
         dispatcher.forward(request, response);
         } /*catch (Exception e) {
         List listeMessageErreur = new LinkedList();
         listeMessageErreur.add(e.toString());
         request.setAttribute("listeMessageErreur", listeMessageErreur);
         RequestDispatcher dispatcher = request
         .getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
         dispatcher.forward(request, response);
         } */catch (Exception e) {
         e.printStackTrace();
         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e
         .toString());
         }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
