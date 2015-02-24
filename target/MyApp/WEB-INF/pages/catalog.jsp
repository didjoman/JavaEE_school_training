<%-- 
    Document   : catalog
    Created on : 24 févr. 2015, 12:02:27
    Author     : Alexandre Rupp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />    
<header>
    <h1>Catalogue</h1>
</header>
<section id="content-wrapper">
    
    <!-- ATTENTION : NE FONCTIONNE PAS ... -->
    <p> 
        <c:if test="${bib ne null}">
        <center>
            <table border="1">
                <caption>Liste des ouvrages</caption>
                <tr>
                    <th>Auteur</th>
                    <th>Titre</th>
                </tr>
                <c:forEach items="${bib}" var="bibEntry">
                    <tr>
                        <td>${bibEntry.get("author")}</td>
                        <td>${bibEntry.get("title")}</td>
                    </tr>
                </c:forEach>
            </table>
        </center>
        
    </c:if>
</p>        
</section>
<footer>
    <a href="library" title="Return">Retour</a>.
</footer>

<jsp:include page="footer.jsp" />