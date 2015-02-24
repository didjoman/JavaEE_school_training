<%-- 
    Document   : logout.jsp
    Created on : 8 févr. 2015, 17:24:35
    Author     : Alexandre Rupp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Déconnexion réussie</h1>
    <p>Vous êtes désormais déconnectés.</p>
    <br />
    Retour à l'<a href="accueil">accueil</a>. <br />
</section>
<jsp:include page="footer.jsp" />