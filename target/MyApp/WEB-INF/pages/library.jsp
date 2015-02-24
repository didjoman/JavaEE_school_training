<%-- 
    Document   : bibliography.jsp
    Created on : 7 févr. 2015, 11:09:04
    Author     : Alexandre Rupp
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<jsp:include page="header.jsp" />    
<header>
    
    <div id="book-fall-animation">
        <img class="books" src="http://i728.photobucket.com/albums/ww284/ccpl_orw/Stock%20photos/FlyingBook.png" style="border: none; width: 150px;" alt="flying book photo FlyingBook.png"/>
    </div>
    <nav>
        <ul id="nav-bar">
            <li><a href="accueil">Index</a> | </li>
            <li><a href="register">S'enregistrer</a> | </li>
            <c:choose>
                <c:when test="${sessionScope.login ne null}">
                <li><a href="logout">Logout</a> | </li>
                </c:when>
                <c:otherwise>
                <li><a href="check_user">Login</a> | </li>
                </c:otherwise>
            </c:choose>
            <li><a href="users">Liste des utilisateurs</a> | </li>
            <li><a href="statistics">Statistiques</a> | </li>
             <li><a href="bilan">Bilan</a> | </li>
            <li><a href="http://ensimag.grenoble-inp.fr/">Ensimag</a></li>
        </ul>
    </nav>
    <h1>Bibliothèque Municipale</h1>
    <nav>
        <ul>
            <li>Accés au <a href="#catalog">catalogue</a>.</li>
            <li>Accés au <a href="#schedule">horraires</a>.</li>                
        </ul>
    </nav>   
</header>
<section id="content-wrapper">
    <h2 id="catalog">Catalogue</h2>
    <ul>
        <li><em>Les Travailleurs de la Mer</em>, Victor Hugo</li>
        <li><em>CSS2 - Pratique du design web</em>, Raphaël Goetter</li>
        <li><em>The C++ Programming Language</em>, Bjame Stroustrup</li>
    </ul>
        
    <h2 id="schedule">Horaires d'ouverture</h2>
    <ul> 
        <li> Lundi :
            <ul>
                <li>10h - 12h</li>
                <li>14h - 18h</li>
            </ul>
        </li>
        <li> Mercredi :
            <ul>
                <li>10h - 16h</li>
            </ul>
        </li>
        <li> Samedi :
            <ul>
                <li>8h - 12h</li>
            </ul>
        </li>
    </ul>
        
</section>
<footer>
    Retour en haut de page 
    <a href="#" title="Back to the top of the page">ici</a>.
</footer>
    
<jsp:include page="footer.jsp" />