<%-- 
    Document   : book_update
    Created on : 3 mars 2015, 16:38:44
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
    <form action="books" method="get" autocomplete="on">
        <label for="author">Auteur : </label>
        <input type="text" name="author" id="author" placeholder="Le nom et prénom de l'auteur" value="${book.author}" required><br />
            
        <label for="title">Titre : </label>
        <input type="text" name="title" id="title" placeholder="Titre du livre" value="${book.title}" required><br />
        <input type="hidden" name="id" value="${book.id}">         
        <input type="hidden" name="action" value="put">         
        <br />
        <input type="submit" value="Enregistrer">
    </form> 
</p>
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />