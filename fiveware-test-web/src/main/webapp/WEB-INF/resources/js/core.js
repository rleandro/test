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