<%-- 
    Document   : login
    Created on : 8 févr. 2015, 11:52:54
    Author     : Alexandre Rupp
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<jsp:include page="header.jsp" />
    
<h1>Connexion</h1>
    
<c:if test="${logged}">
    <section>
        <p>Vous êtes désormais identifié.</p>
    </section>
    <footer>
        <a href="library" title="Return to last page">Retour</a>.
    </footer>
</c:if>
    
<c:if test="${(logged eq null) or (!logged)}">
    <c:if test="${error ne null}">
        <strong style="color: red;">
            ${error}
        </strong><br />
    </c:if>
        
    <p>
    <form method="post" action="check_user">
        <ul>
            <li><label for="login">Login : </label>
                <input type="text" 
                       name="login" 
                       id="login" 
                       <c:if test="${login ne null}">value="${login}"</c:if>
                           placeholder="Votre login" />
                </li>
                <li><label for="password">Password : </label>
                    <input type="password" 
                           name="password" 
                           id="password" 
                    <c:if test="${password ne null}">value="${password}"</c:if>
                        placeholder="Votre password"/>
                </li>
            </ul>
            <input type="submit"/>
        </form>
    </p>
    <footer>
        <a href="library" title="Return to last page">Retour</a>.
    </footer>
</c:if>
<jsp:include page="footer.jsp" />