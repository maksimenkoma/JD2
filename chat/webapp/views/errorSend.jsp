
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
     <head>
            <title>Enter</title>
     </head>
        <body>

           <h4><span style='color: red;'>error of sending the user does not exist</span></h4>

                 <form method="post">

                    <label>Send message to login:
                      <input type="text" name="sender"><br />
                    </label>

                    <label>Message:</br>

                     <textarea name="message" rows="4" cols="55" wrap="virtual"></textarea></br>
                     </label>

                <button type="submit">Send message</button>

                 </form>

               <button onclick="location.href='/helloWitchMe-0.0.0/chats'">My messages</button>

        </body>

</html>