<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">Все пользователи</a>
            <!-- BEGIN -->
            <form action="/users/edit?id=${user.get("id")}" method="post">
                <div class="mb-3">
                    <label for="firstNameInput" class="form-label">Firstname</label>
                    <input type="text" value="${user.get("firstName")}" class="form-control" id="firstNameInput" name="firstName">
                </div>
                <div class="mb-3">
                    <label for="lastNameInput" class="form-label">Lastname</label>
                    <input type="text" value="${user.get("lastName")}" class="form-control" id="lastNameInput" name="lastName">
                </div>
                <div class="mb-3">
                    <label for="emailInput" class="form-label">Email address</label>
                    <input type="email" value="${user.get("email")}" class="form-control" id="emailInput" name="email">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <!-- END -->
        </div>
    </body>
</html>
