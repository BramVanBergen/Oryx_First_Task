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
					href="/home">Home
				</a></li>
				<li class="nav-item active"><a class="nav-link navbar-line"
					href="/products">Products <span class="sr-only">(current)</span></a></li>
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
						<li class="nav-item"><a class="nav-link navbar-line"
							href="/registration">Register</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<div class="gradient"></div>

	<div class="container">
		<h1>Edit product</h1>
		<c:if test="${product.getProductName() !=  ''}">
			<h3>${product.getProductName()}</h3>
		</c:if>

		<form action="editProduct" method="POST">
			<input type="hidden" name="id" value="${product.getId()}" />
			<div class="form-row">
				<div class="col-md-8 form-group">
					<label for="productName">Product name: </label> <input
						class="form-control" type="text" name="productName"
						value="${product.getProductName()}"
						placeholder="${product.getProductName()}" />
				</div>
				<div class="col-md-4 form-group">
					<label for="pricePerUnit">Price per item: </label> <input
						class="form-control" type="number" name="pricePerUnit"
						value="${product.getPricePerUnit()}"
						placeholder="${product.getPricePerUnit()}" />
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-12 form-group">
					<label for="productDescription">Product Description: </label>
					<textarea class="form-control" name="productDescription" rows="5"
						cols="50" placeholder="${product.getProductDescription()}">${product.getProductDescription()}</textarea>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-2 offset-md-9">
					<input type="submit" class="btn save btn-success"
						value="Save changes" />
				</div>
				<div class="col-md-1">
					<a href="/products" class="btn save btn-secondary">Cancel</a>
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