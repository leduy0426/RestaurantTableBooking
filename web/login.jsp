<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - Royal Dragon</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Login</h2>
            <form action="ForStaff" method="post">
                
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <select name="role" required>
                    <option name="role" value="" disabled selected>-- Select Role --</option>
                    <option name="role" value="staff">Staff</option>
                    <option name="role" value="admin">Admin</option>
                </select>
                <button type="submit">Login</button>
                <c:if test="${not empty error}">
                    <div style="color:red;">${error}</div>
                </c:if>

            </form>

        </div>
    </body>
</html>

