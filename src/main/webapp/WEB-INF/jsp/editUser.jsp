<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../css/style.css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav links">
				<li class="nav-item"><a class="nav-link navbar-line"
					href="/home">Home</a></li>
				<li class="nav-item"><a class="nav-link navbar-line"
					href="/products">Products</a></li>
				<c:if test="${user.getRole() == 'ADMIN' }">
					<li class="nav-item"><a class="nav-link navbar-line"
						href="/users">Users</a></li>
				</c:if>

			</ul>
			<ul class="navbar-nav login">
				<c:choose>
					<c:when test="${user.getId() != null}">
						<li class="nav-item active"><a class="nav-link navbar-line"
							href="/profile">${user.getUsername()} <span class="sr-only">(current)</span></a></li>
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/logout">Log out</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/login">Log in</a></li>
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/registration">Register</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</nav>
	<div class="gradient"></div>

	<div class="container">
		<h1>Edit user</h1>
		<form action="/editUser" method="POST">
			<input type="hidden" name="id" value="${userEdit.getId()}">
			<div class="form-row align-items-center profile">
				<div class="col-md-4">
					<label for="firstName">First name: </label> <input
						class="form-control" type="text" name="firstName"
						value="${userEdit.getFirstName()}"
						placeholder="${userEdit.getFirstName()}" />
				</div>
				<div class="col-md-4 offset-md-2">
					<label for="lastName">Last name: </label> <input
						class="form-control" type="text" name="lastName"
						value="${userEdit.getLastName()}"
						placeholder="${userEdit.getLastName()}" />
				</div>
			</div>
			<div class="form-row align-items-center profile">
				<div class="col-md-4">
					<label for="email">Email address: </label> <input
						class="form-control" type="text" name="email"
						value="${userEdit.getEmail()}"
						placeholder="${userEdit.getEmail()}" />
				</div>
				<div class="col-md-4 offset-md-2">
					<label for="username">Username: </label> <input
						class="form-control" type="text" name="username"
						value="${userEdit.getUsername()}"
						placeholder="${userEdit.getUsername()}" />
				</div>
			</div>
			<div class="form-row align-items-center profile">
				<div class="col-md-4">
					<label for="password">Password: </label> <input
						class="form-control" type="password" name="password" id="password"
						value="${userEdit.getPassword()}"
						placeholder="${userEdit.getPassword()}" />
				</div>
				<div class="col-md-2 offset-md-2 align-items-end viewPassword">
					<input class="form-check-input" type="checkbox" name="viewPassword"
						onclick="viewPasswordFunction()" /> <label for="viewPassword">View
						password</label>
				</div>
			</div>
			<div class="form-row align-items-center ">
				<div class="col-md-2 viewPassword">
							<input class="form-check-input" id="role" type="checkbox" ${admin}
								name="role" value="ADMIN" />
							<label for="role">Admin</label>
				</div>
				<div class="col-md-2 offset-md-6 padding-top-35px">
					<input class="btn btn-warning btnSave" type="submit" name="submit" value="Save changes">
				</div>
			</div>
		</form>
	</div>
	<footer>&copy; Bram Van Bergen</footer>
	</div>
	<script type="text/javascript">
		function viewPasswordFunction() {
			console.log("test")
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>