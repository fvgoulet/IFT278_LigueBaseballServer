package liguebaseballServlet;

import javax.servlet.http.*;

import liguebaseball.Connexion;

import java.sql.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Syst�me de gestion de liguebaseball Universit� de Sherbrooke
 */
public class liguebaseballSessionListener implements HttpSessionListener
{

    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("LiguebaseballListener " + se.getSession().getId());
        Connexion connect = (Connexion) se.getSession().getAttribute("Connexion");
        if (connect != null)
        {
            System.out.println("connexion =" + connect.toString());
            try
            {
                connect.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        } /*else
         System.out.println("biblio inaccessible.");
         GestionBibliotheque biblioUpdate = (GestionBibliotheque) se.getSession()
         .getAttribute("biblioUpdate");
         if (biblioUpdate != null) {
         System.out.println("connexion =" + biblioUpdate.cx);
         try {
         biblioUpdate.fermer();
         } catch (SQLException e) {
         System.out.println("Impossible de fermer la connexion");
         e.printStackTrace();
         }
         } else
         System.out.println("biblio inaccessible.");*/

    }
}
