<%-- 
    Document   : book_delete
    Created on : 3 mars 2015, 16:31:30
    Author     : Alexandre Rupp
--%>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Ouvrages</h1>
    <h2>Supprimer une référence</h2>
    <p>
        Etes-vous sûr de vouloir supprimer la référence '${book.title}' par ${book.author} ?
        <button><a href="books?action=delete&id=${book.id}">Oui</a></button> <button><a href="library">Non</a>.</button> 
    </p>
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />