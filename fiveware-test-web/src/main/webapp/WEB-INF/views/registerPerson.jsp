<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>

<script>
	$(function() {
		$("#show").click(function(event) {

			event.preventDefault();
			savePerson();
		});
		$("#hide").click(function() {
			$("#tblPeople").remove();
		})
	});

	function savePerson() {
		var url = window.location.href.toString();

		var name = $('#name').val();

		var person = {}
		person["name"] = $("#name").val();
		person["email"] = $("#email").val();
		person["gender"] = $("#gender").val();
		person["country"] = $("#country").val();
		person["city"] = $("#city").val();
		person["maritalStatus"] = $("#maritalStatus").val();

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : url + "search",
			success : function(data) {
				console.log("SUCCESS: ", data);
				createTable(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		})// end ajax
	};

	function createTable(datas) {
		var table = $("<table class='table table-striped' id='tblPeople'>");
		var head = "<tr>"

		head += "<td>Name </td>";
		head += "<td>Email </td>";
		head += "<td>Gender </td>";
		head += "<td>MaritalStatus </td>";
		head += "<td>Country </td>";
		head += "<td>City </td>";
		table.append(head);
		$("#divTable").append(table);
		$.each(datas.people, function(key, value) {
			var newRow = $("<tr>");
			var cols = "";
			cols += "<td>" + value.name + "</td>";
			cols += "<td>" + value.email + "</td>";
			cols += "<td>" + value.gender + "</td>";
			cols += "<td>" + value.maritalStatus + "</td>";
			cols += "<td>" + value.address.country + "</td>";
			cols += "<td>" + value.address.city + "</td>";
			newRow.append(cols);
			$('#tblPeople').append(newRow);
		});

	}
</script>


<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de pessoas</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Register a Person</a>
		</div>
	</div>
	</nav>
	<div class="container" style="min-height: 900px">
		<form:form action="${spring:mvcUrl('PC#save').build()}"
			class="form-horizontal" id="frm" commandName="person">
			<div class="form-group">
				<div class="col-sm-10">
					<label for="name">Name:</label> <input type="text" id="name"
						name="name" class="form-control">
					<form:errors path="name" />
				</div>
			</div>

			<div class="form-group form-group-lg">
				<div class="col-sm-10">
					<label for="email">Email:</label> <input type="text" id="email"
						name="email" class="form-control">
					<form:errors path="email" />
				</div>
			</div>


			<div class="form-group form-group-lg">
				<div class="col-sm-10">
					<label for="gender">Gender:</label>
					<c:forEach var="opc" items="${genders}">
						<input type="radio" id="gender" name="gender" value="${opc}"
							checked> ${opc}
					</c:forEach>
				</div>
			</div>

			<div class="form-group form-group-lg">
				<div class="col-sm-10">
					<label for="country">Country:</label> <select
						name="address.country" id="country" class="form-control">
						<c:forEach var="c" items="${countries}">
							<option value="${c}">${c}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group form-group-lg">
				<div class="col-sm-10">
					<label for="city">City:</label> <input type="text" id="city"
						name="address.city" class="form-control">
					<form:errors path="address.city" />
				</div>
			</div>
			<div class="form-group form-group-lg">
				<div class="col-sm-10">
					<label>Marital Status:</label>
					<c:forEach items="#{martialStatus}" var="marital">
						<input type="checkbox" name="maritalStatus" id="maritalStatus"
							value="${marital}">${marital}
					</c:forEach>
				</div>
			</div>

			<tr>
				<td><label for="">:</label>
				<td><input type="submit" name="txtSubmit" value="Register"
					class="btn btn-primary btn-lg" id="btnRegister"></td>
		</form:form>
		<br> <input class="btn btn-default" type="button" id="show"
			value="Show people"> <input class="btn btn-default"
			type="button" id="hide" value="Hide people">

		<div id="divTable">
			<h3>Lista de Pessoas:</h3>

		</div>
	</div>
</body>
</html>