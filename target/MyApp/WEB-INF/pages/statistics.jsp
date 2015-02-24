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
    <h1>Statistiques</h1>
</header>
<section id="content-wrapper">
    <p> 
        Répartition des villes de naissance des adhérents de la bibliothèque :
            
                <img src="stat_img?${imgParams}legend=true"/>
    </p>        
</section>
<footer>
    <a href="library" title="Back to the top of the page">Retour</a>.
</footer>
    
<jsp:include page="footer.jsp" />