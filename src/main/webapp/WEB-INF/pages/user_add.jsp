<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp" />
<section>
    <h1>Inscription</h1>
    <c:if test="${errors ne null}">
        <c:forEach items="${errors}" var="error">  
            <strong style="color: red;">
                ${error}
            </strong><br />
            </c:forEach> 
            <br />
    </c:if>
            
    <form action="register" method="post" autocomplete="on">
        <label for="lastName">Nom : </label>
        <input type="text" name="lastName" id="lastName" placeholder="Votre nom" required><br />
        
        <label for="firstName">Prénom : </label>
        <input type="text" name="firstName" id="firstName" placeholder="Votre prénom" required><br />
        
        <label for="login">Login : </label>
        <input type="text" name="login" id="login" placeholder="Votre login" required><br />
        
        <label for="password">Password : </label>
        <input type="password" name="password" id="password" placeholder="Votre mot de passe" autocomplete="off" required><br />
        <br />
        
        <fieldset required>
            <legend>Sexe : </legend>
            <input type="radio" name="gender" value="male" /> M<br />
            <input type="radio" name="gender" value="female" /> F<br />
        </fieldset>
        <br />
        <fieldset required>
            <legend>Age : </legend>
            <input type="radio" name="age" value="jeune" /> 16-18<br />
            <input type="radio" name="age" value="presque jeune" /> 18-30<br />
            <input type="radio" name="age" value="vieux" /> autre<br />
        </fieldset>
        <br />
        Ville de naissance : 
        <select name="birthTown" required>
            <option value="Grenoble">Grenoble</option>
            <option value="Lyon">Lyon</option>
            <option value="Paris">Paris</option>
            <option value="Autre">Autre</option>
        </select><br /><br />
        <input type="submit" value="soumettre">
    </form> 
</p>
</section>
<footer>
    <a href="library" title="Return to last page">Retour</a>.
</footer>
<jsp:include page="footer.jsp" />
