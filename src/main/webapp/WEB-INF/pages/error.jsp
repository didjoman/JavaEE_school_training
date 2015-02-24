<%-- 
    Document   : statistics.jsp
    Created on : 7 févr. 2015, 11:09:04
    Author     : Alexandre Rupp
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<jsp:include page="header.jsp" />    
<header>
    <h1>Erreur</h1>
</header>
<section id="content-wrapper">
    <p>
        <c:choose>
            <c:when test="${statusCode != 500}">
            <h3>Error Details</h3>
            <strong>Status Code</strong>: ${statusCode} <br>
            <strong>Requested URI</strong>: ${requestUri}
                
        </c:when>
        <c:otherwise>
            <h3>Exception Details</h3>
            <ul>
                <li><strong>Servlet Name</strong>: ${servletName}</li>
                <li><strong>Exception Name</strong>: ${throwable.getClass().getName()}</li>
                <li><strong>Requested URI</strong>: ${requestUri}</li>
                <li><strong>Exception Message</strong>: ${throwable.getMessage()}</li>
            </ul>
        </c:otherwise>
    </c:choose>
</p>        
</section>
<footer>
    <a href="${requestUri}" title="Return to last page">Retour</a>.
</footer>
    
<jsp:include page="footer.jsp" />