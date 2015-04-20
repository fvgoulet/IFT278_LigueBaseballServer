package liguebaseballServlet;

import javax.servlet.*;
import java.util.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Syst�me de gestion de biblioth�que &copy; 2004 Marc Frappier, Universit� de
 * Sherbrooke
 */

public class liguebaseballContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Contexte d�marr�:"
				+ sce.getServletContext().getServletContextName());
		System.out
				.println("Voici les param�tres du contexte tels que d�finis dans web.xml");
		Enumeration initParams = sce.getServletContext()
				.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String name = (String) initParams.nextElement();
			System.out.println(name + ":"
					+ sce.getServletContext().getInitParameter(name));
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out
				.println("Le contexte de l'application GestionBibliotheque vient d'�tre d�truit.");
	}
}
