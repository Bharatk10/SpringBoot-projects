<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="/images/logo.png">
<meta charset="ISO-8859-1">
<title>Zetta Library</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" />
<script src="https://kit.fontawesome.com/c323173fb6.js"
	crossorigin="anonymous"></script>
</head>
<body class="bg-info">

	<h1 class="text-center mt-4 text-danger">Zetta Library</h1>

	<table class="table w-50 mt-2 mx-auto">
		<thead class="table-dark">
			<tr>
				<th>Book</th>
				<th>Title</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="book">

				<tr>
					<td class="w-20">${book.bookId}</td>
					<td class="w-20">${book.bookName}</td>
					<td class="w-20">${book.bookPrice}</td>
					<td class="w-20"><a href="view?isbn:${book.bookId} "><i class="fas fa-edit"></i></a> &nbsp;&nbsp;<i
						class="fa-solid fa-trash" style="color: #f22121;"></i>
						&nbsp;&nbsp;<i class="fa-solid fa-eye"></i></td>
				</tr>

			</c:forEach>

		</tbody>
	</table>

</body>
</html>

