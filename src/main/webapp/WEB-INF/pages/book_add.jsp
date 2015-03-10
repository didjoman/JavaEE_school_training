<%-- 
    Document   : book_add
    Created on : 3 mars 2015, 16:24:25
    Author     : Alexandre Rupp
--%>


<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Ouvrages</h1>
    <h2>Ajouter une référence</h2>
    
    <p>
    <form action="books" method="post" autocomplete="on">
        <label for="author">Auteur : </label>
        <input type="text" name="author" id="author" placeholder="Le nom et prénom de l'auteur" required><br />
        
        <label for="title">Titre : </label>
        <input type="text" name="title" id="title" placeholder="Titre du livre" required><br />
        
        <br />
        <input type="submit" value="Ajouter">
        <input type="reset" value="Réinitialiser">
    </form> 
</p>
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />

