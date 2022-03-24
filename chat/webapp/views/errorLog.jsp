
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
     <head>
            <title>Enter</title>
     </head>
        <body>
           <h4><span style='color: red;'>incorrect login or password try again or register</span></h4>

                 <form method="post">
                    <label>Login:
                      <input type="text" name="login"><br />
                    </label>

                    <label>Password:
                         <input type="password" name="pass"><br />
                    </label>


                       <button type="submit">Login</button>
                 </form>

                <button onclick="location.href='/helloWitchMe-0.0.0/signUp'">Registration</button>
        </body>

</html>