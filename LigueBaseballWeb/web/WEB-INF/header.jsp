<%-- 
    Document   : header
    Created on : Apr 20, 2015, 10:20:10 PM
    Author     : fvgou_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion baseball</title>
        <table style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;" border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <tr>
                    <td> <%

    response.sendRedirect("/LigueBaseballWeb/equipe");
%></td>
                    <td> <a href="/LigueBaseballWeb/web/WEB-INF/src/equipe.jsp"> Equipe </a> </td>
                    <td> <a href="/LigueBaseballWeb/joueur"> Joueur </a> </td>
                    <td> <a href="/LigueBaseballWeb/match"> Match </a> </td>
                </tr>
            </tbody>
        </table>
    </head>
</html>
