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

/**
 *
 * @author fvgou_000
 */
public class header extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("arbitrer") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/arbitre.jsp");
            dispatcher.forward(request, response);
        }
     else if (request.getParameter("equipe") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/equipe.jsp");
            dispatcher.forward(request, response);
        }
       else if (request.getParameter("joueur") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/joueur.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("match") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/match.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("logout") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
            request.removeAttribute("Connexion");
            request.removeAttribute("ligueUpdate");
            request.getSession().setAttribute("etat", -1);
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
                throw new Exception("Aucun pernom entrée");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/src/joueur.jsp");
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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
