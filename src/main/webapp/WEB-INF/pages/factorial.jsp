<%-- 
    Document   : statistics.jsp
    Created on : 7 févr. 2015, 11:09:04
    Author     : Alexandre Rupp
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<jsp:include page="header.jsp" />    
<header>
    <h1>Factorielle</h1>
</header>
<section id="content-wrapper">
    
    <!-- ATTENTION : NE FONCTIONNE PAS ... -->
    <p> 
    <form method="post" action="factorial">
        <ul>
            <li>
                <label for="number">Votre nombre : </label>
                <input type="text" name="number" id="number" placeholder="Votre nombre" />
            </li>
        </ul>
        <input type="submit"/>
    </form>
    <c:if test="${facto != null}">
        resultat : ${facto}
    </c:if>
</p>        
</section>
<footer>
    <a href="library" title="Back to the top of the page">Retour</a>.
</footer>
    
<jsp:include page="footer.jsp" />