<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Résumé</h1>
    <ul>
        <li>Vous vous appelez ${lastName}</li>
        <li>Vous êtes de sexe ${gender} </li>
        <li>Login : ${login}</li>
        <li>Mot de passe : ${password}</li>
        <li>Vous êtes ${age}</li>
        <li>Vous êtes nés à ${birthTown}</li>
    </ul>
    <br />
    Retour à l'<a href="accueil">accueil</a>. <br />
</section>
<jsp:include page="footer.jsp" />
