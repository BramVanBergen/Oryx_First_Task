<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Spring Security Example</title>
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
					href="/home">Home <span class="sr-only">(current)</span>
				</a></li>
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
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/profile">${user.getUsername()}</a></li>
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/logout">Log out</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/login">Log in</a></li>
						<li class="nav-item active"><a class="nav-link navbar-line"
							href="/registration">Register <span class="sr-only">(current)</span></a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</nav>
	<div class="gradient"></div>

	<div class="container">
		<form action="/registration" method="post">
			<div class="form-row">
				<div class="form-group col-md-5">
					<label for="firstName">First name: </label> <input type="text"
						class="form-control" name="firstName" />
				</div>
				<div class="form-group col-md-5 offset-md-2">
					<label for="lastName">Last name: </label> <input type="text"
						class="form-control" name="lastName" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-5">
					<label for="email">Email: </label> <input type="text"
						class="form-control" name="email" />
				</div>
				<div class="form-group col-md-5 offset-md-2">
					<label for="username">Username: </label> <input type="text"
						class="form-control" name="username" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-5">
					<label for="password">Password: </label> <input type="password"
						class="form-control" name="password" />
				</div>
				<div class="form-group col-md-5 offset-md-2">
					<label for="confirmPassword">Confirm password: </label> <input
						type="password" class="form-control" name="confirmPassword" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col">
					<input type="submit" value="Sign up"
						class="btn btn-success col-md-2" /> <a href="login"
						class="btn btn-secondary col-md-2">Log in</a>
				</div>
			</div>
		</form>
		<footer>&copy; Bram Van Bergen</footer>
	</div>
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