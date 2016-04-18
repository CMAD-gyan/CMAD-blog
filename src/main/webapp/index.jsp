<html>
<head>
<script src="scripts/jquery-2.1.3.js"></script>
<script>
$(document).ready(function(){
	$.get("rest/user", function(data){
		var rowTemplate = $("#templates table").html();
		console.log(rowTemplate);
		for(index in data){
			var row =  rowTemplate.replace("username",data[index].username).replace("name",data[index].name).replace("emailId",data[index].email).replace("score",data[index].score).replace("active",data[index].active);
			$("#users").append(row);
		}
	});
});
</script>
</head>
<body>
<div id="templates" style="display: none;">
	<table>
		<tr><td>username</td><td>name</td><td>emailId</td><td>score</td><td>active</td></tr>
	</table>
</div>
<table id="users" border="1">
<tr><td>UserName</td><td>Name</td><td>EmailID</td><td>Score</td><td>Active</td></tr>
</table>
</body>
</html>
