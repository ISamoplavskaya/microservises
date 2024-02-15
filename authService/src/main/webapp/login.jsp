<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Sign In</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_style.css" />
    </head>

    <body>

    <% String message = (String) request.getAttribute("errorMessage"); %>

        <div class="form-wrap">
            <form action="/auth" method="post">
                <h1>Sign In</h1>
                <input type="text" placeholder="Username" id="username" name="username">
                <input type="password" placeholder="Password" id="password" name="password">
                <input type="submit" id="sub_btn" value="Sign In">
            </form>

            <% if (message != null) { %>
            <p id="message"><%=message%></p>
            <% } %>

        </div>

    </body>
</html>