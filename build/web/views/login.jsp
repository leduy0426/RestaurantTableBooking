<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <form action="LoginServlet" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <select name="role" required>
            <option value="">-- Select Role --</option>
            <option value="staff">Staff</option>
            <option value="admin">Admin</option>
        </select>
        <button type="submit">Login</button>
    </form>
    <a href="index.jsp" class="back">← Back to Home</a>
</div>
</body>
</html>

