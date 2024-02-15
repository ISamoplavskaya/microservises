<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_style.css" />
</head>
<body>
    <div class="form-wrap">
        <h1 align="center">Hi!</h1>
        <br>
        <h2 align="center">You need to log in to get to the user control panel.</h2>
        <br>
        <br>

        <table align="center" style="collapse: collapse; font-size: 20px">
            <tr align="center">
               <td width="100"><a href="/auth" class="button">Login</a></td>
            </tr>
        </table>
    </div>

</body>
</html>
