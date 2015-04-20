package liguebaseballServlet;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import liguebaseball.Connexion;

/**
 * Classe pour login système de gestion de bibliothèque
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class Login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			// fermer la session si elle a déjà été ouverte lors d'un appel
			// précédent
			// survient lorsque l'usager recharge la page login.jsp
			if (session.getAttribute("etat") != null) {
				// pour déboggage seulement : afficher no session et information
				System.out
						.println("GestionBibliotheque: session déja crée; id="
								+ session.getId());
				// la méthode invalidate appelle le listener
				// BiblioSessionListener; cette classe est chargée lors du
				// démarrage de
				// l'application par le serveur (voir le fichier web.xml)
				session.invalidate();
				session = request.getSession();
				System.out.println("GestionBibliotheque: session invalidée");
			}

			// lecture des paramètres du formulaire login.jsp
			String userId = request.getParameter("userIdBD");
			String motDePasse = request.getParameter("motDePasseBD");
			String serveur = request.getParameter("serveur");
			String adresseIP = request.getParameter("adresseIP");
			String bd = request.getParameter("bd");

			/*
			 * ouvrir une connexion avec la BD et créer les gestionnaires et
			 * stocker dans la session. On ouvre une session en lecture readcommited pour les interrogations
			 * seulement et une autre en mode serialisable, pour les transactions
			 */
			System.out.println("Login: session id=" + session.getId());
			Connexion conection = new Connexion(serveur, bd,
					userId, motDePasse);
			session.setAttribute("Connexion", conection);

			// afficher le menu membre en appelant la page selectionMembre.jsp
			// tous les JSP sont dans /WEB-INF/
			// ils ne peuvent pas être appelés directement par l'utilisateur
			// seulement par un autre JSP ou un servlet
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
			dispatcher.forward(request, response);
			session.setAttribute("etat", new Integer(baseballConstantes.CONNECTE));
		} catch (SQLException e) {
			List listeMessageErreur = new LinkedList();
			listeMessageErreur.add("Erreur de connexion au serveur");
			listeMessageErreur.add(e.toString());
			request.setAttribute("listeMessageErreur", listeMessageErreur);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			// pour déboggage seulement : afficher tout le contenu de
			// l'exception
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					e.toString());
		}
	}

	// Dans les formulaire, on utilise la méthode POST
	// donc, si le servlet est appelé avec la méthode GET
	// on retourne un page d'erreur, afin de ne pas permettre
	// à l'utilisateur d'appeler un servler directement
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Accès
		// invalide");
		doPost(request, response);
	}

} // class
