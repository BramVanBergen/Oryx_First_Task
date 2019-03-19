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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link href="../css/style.css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light container">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link navbar-line"
					href="/home">Home </a></li>
				<li class="nav-item active"><a class="nav-link navbar-line"
					href="/products">Products <span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link navbar-line"
					href="/orders">Orders</a></li>
				<c:if test="${user.getRole() == 'ADMIN' }">
					<li class="nav-item"><a class="nav-link navbar-line"
						href="/users">Users</a></li>
				</c:if>
			</ul>
			<c:choose>
				<c:when test="${user.getRole() == 'ADMIN' }">
					<ul class="navbar-nav offset-md-4">
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav offset-md-5">
				</c:otherwise>
			</c:choose>
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
		<h1>Products</h1>
		<c:choose>
			<c:when test="${!products.isEmpty() }">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Price</th>
								<th>Amount</th>
								<th>Order</th>
								<c:if test="${user.getRole() == 'ADMIN'}">
									<th>Edit</th>
									<th>Delete</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${products}" var="product">
								<form action="saveOrder" method="POST">
									<input type="hidden" value="${user.getId()}" name="userId">
									<input type="hidden" value="${product.getId()}"
										name="productId">
									<tr>
										<td>${product.getProductName()}</td>
										<td>${product.getProductDescription()}</td>
										<td>&euro; ${product.pricePerUnit}</td>
										<td><input type="number" name="amount"
											class="form-control" /></td>
										<td><input type="submit" name="submit"
											class="btn btn-success" value="Order" /></td>
										<c:if test="${user.getRole() == 'ADMIN'}">
											<td><a href="editProduct?id=${product.getId()}"><i
													class="fas fa-pencil-alt"></i></a></td>
											<td><a href="deleteProduct?id=${product.getId()}"><i
													class="fas fa-trash-alt"></i></a></td>
										</c:if>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<h4>There are currently no products.</h4>
			</c:otherwise>
		</c:choose>
		<c:if test="${user.getRole() == 'ADMIN'}">
			<div class="row">
				<div class="col-md-2 offset-md-10">
					<a href="/editProduct" class="btn save btn-success">New product</a>
				</div>
			</div>
		</c:if>
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