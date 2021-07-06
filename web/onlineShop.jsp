<%-- 
    Document   : onlineShop
    Created on : Jun 17, 2021, 9:32:16 AM
    Author     : keith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="kiet.Item.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Online Shop</h1>
        <form action="MainController">
            Choice item 


            <c:set var="list" value="${requestScope.PRODUCT}"/>
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name Product</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${list}" varStatus="counter">

                            <tr>
                                <td>${ counter.count }</td>
                                <td>${dto.name}</td>
                                <td>${dto.price}</td>
                        <form action="MainController">
                            <td>
                                <input type="hidden" name="txtId" value="${dto.id}" />
                                <input type="submit" value="Add to cart" name="btnAction" />

                            </td>
                        </form>
                        </tr>

                    </c:forEach>
                        
                    </tbody>
                    
                </table>

            </c:if>

           
            <input type="submit" value="View your cart" name="btnAction" /><br/>
            <a href="login.html">Return to Login Page</a>
        </form>
    </body>
</html>
