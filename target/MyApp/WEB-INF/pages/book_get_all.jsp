<%-- 
    Document   : book_get_all
    Created on : 3 mars 2015, 16:22:00
    Author     : Alexandre Rupp
--%>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Ouvrages</h1>
    
    <c:if test="${books ne null}">
        <table>
            <tr>
                <th>Auteur</th>
                <th>Titre</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                    <td><a href="books?action=get&view=update&id=${book.id}">modifier</a>
                    </td>
                    <td><a href="books?action=get&view=delete&id=${book.id}">supprimer</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />

