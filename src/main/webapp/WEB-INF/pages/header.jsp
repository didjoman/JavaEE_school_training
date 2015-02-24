<%-- 
    Document   : header
    Created on : 6 févr. 2015, 12:23:47
    Author     : Alexandre Rupp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- META INFOS -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- STYLE -->
        <link rel="stylesheet" media="screen" type="text/css" title="Design"
	href="css/normalize.css" />
        <link rel="stylesheet" media="screen" type="text/css" title="Design"
	href="css/styles.css" />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <!-- SCRIPTS -->
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </head>
    <body <c:if test="${isIndex != null}"> id="index" </c:if> >