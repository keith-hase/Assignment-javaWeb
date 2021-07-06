<%-- 
    Document   : viewCart.jsp
    Created on : Jun 17, 2021, 10:18:19 AM
    Author     : keith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="kiet.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

     

        <c:if test="${not empty sessionScope}">
            <h1>Your cart include</h1>
            <c:set var="cart" value="${sessionScope.CART}"/>

            <c:if test="${not empty cart}">
                <c:set var="detailItem" value="${sessionScope.DETAILITEM}" />
                <c:if test="${not empty detailItem}">
                        <c:set var="items" value="${sessionScope.ITEMS}"/>
                    <form action="MainController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <c:forEach var="detail" items="${detailItem}" varStatus="counter">
                                <c:forEach var="key" items="${items.keySet()}">
                                    <c:if test="${detail.id eq key }">
                                        <c:set var="total" value="${detail.price * items.get(key)}"/>
                                        <tbody>

                                            <tr>
                                                <td>
                                                    ${counter.count}
                                                 
                                                </td>
                                                <td>
                                                    ${detail.name}
                                                  
                                                </td>
                                                <td>
                                                    ${items.get(key)}
                                             
                                                </td>
                                                <td>
                                                    ${detail.price}
                                               
                                                </td>
                                                <td>
                                                    ${total}
                                                    
                                                </td>
                                                <td>
                                                    <c:set var="totalAllPrice" value="${totalAllPrice+total}" />
                                                    <input type="checkbox" name="chkItem" value="${key}" />
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                <tr >

                                    <td colspan="4">
                                        Total: 
                                    </td>
                                    <td colspan="1">
                                        <b><font color="red"> ${totalAllPrice} $ </font></b>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove selected items" name="btnAction" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <input type="submit" value="View shop" name="btnAction" />

                                    </td>
                                    <td colspan="2">

                                        <input type="submit" value="Checkout Order" name="btnAction" /> 
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </form>
                </c:if>
            </c:if>

            <c:if test="${empty items}">
                <h2> <font color="red"> Your cart is empty</font></h2></br>
                <a href="MainController?btnAction=View shop">Go to shopping</a>   
            </c:if>
        </c:if>
        <font color="red"> ${requestScope.REMOVEERROR} </font>

 
    </body>
</html>
