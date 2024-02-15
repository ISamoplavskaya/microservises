<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Add Employee</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add_employee.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css" />
    </head>
    <body>
        <% String message = (String) request.getAttribute("already_exist"); %>
        <div class="form-wrap">
            <form action="/addEmployee" method="post">
                <h1>Add new employee</h1>
                <input type="text" placeholder="Personnel number" id="personnelNumber" name="personnelNumber">
                <input type="text" placeholder="Full name" id="fullName" name="fullName">
                <input type="text" placeholder="Position" id="position" name="position">
                <input type="text" placeholder="Category" id="category" name="category">
                <input type="text" placeholder="Hourly rate" id="hourlyRate" name="hourlyRate">
                <% if (message != null) { %>
                <p id="message"><%=message%></p>
                <% } %>
                <input type="submit" id="sub_btn" value="Add">
                <input type="button" value="Cancel" onclick="window.location.href='/employees'">

             </form>
        </div>
    </body>
</html>