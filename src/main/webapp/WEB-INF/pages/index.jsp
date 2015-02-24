<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>My Awesome Application</h1>
    Accés à la <a href="library">bibliothèque</a>. <br />
    Accés à la page de <a href="factorial">Factorielle</a>. <br />
    <p>${welcomeMsg}
        <c:if test="${msg != null}">
            <br />${msg}
        </c:if>    
        <form action="accueil" method="post">
           ${order} &nbsp;
           <input type="text" name="name" value="" />
           <input type="submit" />
        </form> 
    </p>
</section>
<jsp:include page="footer.jsp" />
