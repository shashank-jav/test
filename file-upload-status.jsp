<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container py-5">
    <div class="row">
        <div class="col-10 mx-auto">
            <h1>File Upload Status</h1>
            <!--display error if any-->
            <div class="alert alert-danger" role="alert" th:if="${!status}">
                <strong>Error:</strong>
                <span th:text="${message}"></span>
            </div>

            <!--display users list-->
            <table class="table table-striped" th:if="${status}">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                  
                </tr>
                </thead>
                <tbody>
                <tr th:each="user, i : ${users}">
                    <th scope="row" th:text="${i.index + 1}"></th>
                    <td th:text="${user.id}"></td>
                    <td th:text="${user.name}"></td>
                    <td th:text="${user.email}"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>