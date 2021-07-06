<%-- 
    Document   : admin
    Created on : May 13, 2021, 11:34:20 AM
    Author     : keith
--%>

<%@page import="java.util.List"%>
<%@page import="kiet.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>



        <br/>

        <h1>Welcome : <%--<%= session.getAttribute("USERNAME")%>--%>  ${sessionScope.LASTNAME} </h1><!-- điền fullname -->

        <br/>
        <form action="MainController">
            <!--            giá trị search phải đc giữ lại nếu ko giữ là toang-->
            Search: <input type="text" name="txtSearch" value=${param.txtSearch} ><br/>
            <input type="submit" value="Search" name="btnAction" />

            <input type="submit" name="btnAction" value="Logout" /><br/>
            <input type="submit" value="View shop" name="btnAction" />
        </form>
       
        <!--        doc du lieu ra -->
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.INFOR}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Lastname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="MainController">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                     <input type="text" name="txtID" value="${dto.username}" />
                                </td>
                                <td>
                                    
                                    <input type="text" name="txtLastname" value="${dto.lastname}" />
                                </td>
                                <td>${dto.role}
                                    <input type="checkbox" name="cbRole" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                    
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="btnAction" value="Delete"/>
                                        <c:param name="id" value="${dto.username}"/>
                                        <c:param name="txtSearchValue" value="${param.txtSearch}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btnAction"/>
                                    <input type="hidden" name="keyword" value="${param.txtSearch}" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${ empty result}">
                FOUND NOTHING!!
            </c:if>
        </c:if>





        <%--  <%
              List<AccountDTO> result = (List<AccountDTO>) request.getAttribute("INFOR");
              if (result != null) {
                  if (result.size() > 0) {


        %>
        <h2>Search Result</h2>

        <table border="1">
            <thead>

                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%  int count = 0;
                    for (AccountDTO dto : result) {
                        count++;

                %>
            <form action="MainController" method="POST">
                <tr>

                    <td><%= count%></td>
                    <td><%= dto.getUsername()%>
                        <input type="hidden" name="txtID" value="<%= dto.getUsername()%>"/>
                    </td>
                    <td><input type="text" name="txtLastname" value="<%= dto.getLastname()%>"></td>
                    <td><%= dto.isRole()%>
                        <input type="checkbox" name="cbRole" value="ON"
                               <% if(dto.isRole()){ %> checked="checked"<% } %>/>
                       
                        
                    </td>
                    <!-- kĩ thuật delete này là kĩ thuật tĩnh ko có lưu bất cứ param hay dữ liệu lưu ở phía client -->
                    <td> <a href="MainController?btnAction=Delete&id=<%= dto.getUsername()%>&txtSearchValue=<%= request.getParameter("txtSearch")%>">Delete</a> </td>
                    <!-- Kĩ thuật hidden from field nâng cao hơn -->
                    <td> 
                        <input type="hidden" name="keyword" value="<%= request.getParameter("txtSearch") %>" />
                        <input type="submit" name="btnAction" value="Update"/>
                    </td>

                </tr>
            </form>
            <%
                }
            %>
        </tbody>

    </table>
    <%
    } else {

    %>
    <font color='red'>
    Not found
    </font>
    <%     }
        }
    %>
        --%>


    </body>
</html>
