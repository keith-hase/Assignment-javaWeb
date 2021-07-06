<%-- 
    Document   : insert
    Created on : Jun 30, 2021, 12:00:38 PM
    Author     : keith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sign up</h1>
        <form action="MainController" method="POST">
            <c:set var="error" value="${requestScope.ERRORDETAIL}"/>
            Username* : <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:if test="${not empty error}">
                <font color="red">${error.usernameLengthErr}</font>
            </c:if>   
            <c:if test="${not empty error}">
                <font color="red">${error.usernameIsExisted}</font>
            </c:if>   
           <br/> Password* : <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty error}">
                <font color="red">${error.passwordLengthErr}</font>
            </c:if>   
           <br/> Confirm Password* : <input type="text" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty error}">
                <font color="red">${error.confirmNotMatch}</font>
            </c:if>   
          <br/>  Full name* : <input type="text" name="txtLastname" value="${param.txtLastname}" /><br/>
            <c:if test="${not empty error}">
                <font color="red">${error.lastnameLengthErr}</font>
            </c:if>   
           <br/> <input type="submit" value="Create new account" name="btnAction" />
            <input type="reset" value="Reset" /><br/>
            <a href="login.html">Return login page</a>
        </form>
    </body>
</html>
