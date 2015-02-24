<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Utilisateurs</h1>
    
    <c:if test="${users ne null}">
        <table>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>login</th>
                <th>password</th>
                <th>Homme ?</th>
                <th>Age</th>
                <th>Ville de naissance</th>
            </tr>
            <c:forEach items="${users}" var="user">  
                <tr>
                    <td>${user.lastName}</td>
                    <td>${user.firstName}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.isMale}</td>
                    <td>${user.age}</td>
                    <td>${user.town}</td>
                </tr>
            </c:forEach> 
        </table>
        <br />
    </c:if>
    
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />
