<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Employees</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee_style.css" />
</head>
<body>
<br>
<h2 align="center">List of employees</h2>
<div align="center">
    <form action="/addEmployee" method="get">
        <input id="add_employee_button" type="submit" value="Add new employee">
    </form>
</div>
<br>
<div align="center">
    <c:if test="${not empty listEmployees}">
        <form action="/update" method="post" id="updateForm">
            <table>
                <tr>
                    <th width="70">ID</th>
                    <th width="80">Personnel number</th>
                    <th width="250">Full name</th>
                    <th width="200">Position</th>
                    <th width="80">Category</th>
                    <th width="100">Hourly rate</th>
                    <th width="80">Hours worked per month</th>
                    <th width="80">Night hours per month</th>
                    <th width="80">Weekend hours per month</th>
                    <th width="80">Harmfulness, %</th>
                    <th width="80">Part-time, %</th>
                    <th width="80">Premium</th>
                    <th width="100">Full salary</th>
                    <th width="100">Delete</th>
                </tr>
                <c:forEach var="employee" items="${listEmployees}" varStatus="loop">
                    <tr align="center">
                        <td><input type="text" name="employeeId${loop.index}" value="${employee.getId()}"></td>
                        <td><input type="text" name="personnelNumber${loop.index}" value="${employee.getPersonnelNumber()}"></td>
                        <td><input type="text" name="fullName${loop.index}" value="${employee.getFullName()}"></td>
                        <td><input type="text" name="position${loop.index}" value="${employee.getPosition()}"></td>
                        <td><input type="text" name="category${loop.index}" value="${employee.getCategory()}"></td>
                        <td><input type="text" name="hourlyRate${loop.index}" value="${String.format(Locale.ENGLISH, "%.2f", employee.getHourlyRate())}"></td>
                        <td><input type="text" name="hoursWorked${loop.index}" value="${employee.getHoursWorked()}"></td>
                        <td><input type="text" name="nightHours${loop.index}" value="${employee.getNightHours()}"></td>
                        <td><input type="text" name="weekendHours${loop.index}" value="${employee.getWeekendHours()}"></td>
                        <td><input type="text" name="harmfulness${loop.index}" value="${employee.getHarmfulness()}"></td>
                        <td><input type="text" name="partTime${loop.index}" value="${employee.getPartTime()}"></td>
                        <td><input type="text" name="premium${loop.index}" value="${employee.getPremium()}"></td>
                        <td>${String.format(Locale.ENGLISH, "%.2f", employee.getFullSalary())}</td>
                        <td>
                            <!-- Каждая кнопка "Remove" вызывает JavaScript функцию для отправки формы удаления -->
                            <button type="button" onclick="removeEmployee(${employee.getId()})">Remove</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div align="right">
                <input type="submit" value="Update and calculate the salary" id="update_and_calculate" style="margin-right: 150px; margin-top: 20px;">
            </div>
        </form>
    </c:if>
</div>

<script>
    function removeEmployee(employeeId) {
        if (confirm("Are you sure you want to remove this employee?")) {
            var form = document.createElement("form");
            form.setAttribute("method", "get");
            form.setAttribute("action", "/removeEmployee");

            var input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "id");
            input.setAttribute("value", employeeId);
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>

</body>
</html>
