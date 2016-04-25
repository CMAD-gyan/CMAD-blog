<html>

<head>
	<title>Blog</title>
	<link rel="stylesheet" type="text/css" href="css/myStyle.css">
	<link rel="stylesheet" type="text/css" href="css/mysigup.css">

    <script src="scripts/jquery-2.1.3.js"></script>
	
    <script>
    var dataBackup = null;
        $(document).ready(function() {
			
      $("#signup").click(function (e) {
		
		var name = $("input#name").val();
		var username = $("input#username").val();
		var mail = $("input#mail").val();
		var password = $("input#password").val();
		
		var data = JSON.stringify({name:name, username:username, email: mail, password: password});
        
        
        $.ajax({
            type : "POST",
            url : 'rest/users',
            dataType :"json",
            contentType: "application/json",
            data : data,
            success : function(result) {
          		  alert ("User: "+ name +" is registered");
          		  window.location="index.jsp";
          	  
  },
  });
        });
		
		
    });

	
		
    </script>
</head>

<body>
    
	<div  class="nav">
      <ul>
        <li> <a  class="active"  href="index.jsp">Home</a></li>
        <li> <a  href="#contact">Contact</a></li>
        <li> <a  href="#about">About</a></li>
        <li  style="float:right"><a  href="signup.jsp"></a></li>
        <li  style="float:right"><a  href="#login">Login</a></li>
      </ul>
    </div>
	<div>
      <h1 align="center" >Welcome To CMAD BLOG</h1>
	</div>
	<br/><br/>
	
	<form action="index.jsp" id="submitForm" method="post"  novalidate="novalidate">
      
        <h1>Sign Up</h1>
        
        <fieldset>
          
          <label for="name">Name:</label>
          <input type="text" id="name" name="user_name">
          
		  <label for="username">UserName:</label>
          <input type="text" id="username" name="user_username">
		  
          <label for="mail">Email:</label>
          <input type="email" id="mail" name="user_email">
          
          <label for="password">Password:</label>
          <input type="password" id="password" name="user_password">
          
        </fieldset>
        
        <button id="signup" type="submit">Sign Up</button>
      </form>
</body>

</html>