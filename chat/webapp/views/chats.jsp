<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Chats</title>
    </head>
        <body>
                <p>User <c:out value="${user}"/> received messages from</p>

                   <c:forEach var="message" items ="${history}">
                        <li>${message}</li>
                   </c:forEach>

                    <button onclick="location.href='/helloWitchMe-0.0.0/message'">Send message</button>

        </body>

</html>
