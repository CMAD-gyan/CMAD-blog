<html>

<head>
	<title>Blog</title>
	<link rel="stylesheet" type="text/css" href="css/myStyle.css">
    <script src="scripts/jquery-2.1.3.js"></script>
    <script>
    var dataBackup = null;
        $(document).ready(function() {
        	
        $.get("rest/questions", function (data){
            for(var i=0; i<data.length; i++) {
				var rowTemplate = $("#sampleQuestionTemp").html();
				console.log(rowTemplate);
				console.log(data[i].title)
				var newID = "id=\""+data[i].questionId+"\"";
				console.log(newID);
				rowTemplate = rowTemplate.replace("id=\"\"",newID);
				var qrow = rowTemplate.replace("questionTitle", data[i].title);
               	$("#sampleQuestions").append(qrow);
				//$("#sampleQuestions a").attr("id",data[i].questionId)
               }
        });
        	
		$.get("rest/questions", function(data){
			var rowTemplate = $("#questionTemplate").html();
			console.log(rowTemplate);
			var index;
			for(index=0; index<1;index++){
				var qrow =  rowTemplate.replace("questionTitleV",data[index].title).replace("questionTextV",data[index].text).replace("quserNameV",data[index].username).replace("viewCountsV",data[index].viewsCount)
				console.log("append answer start");
				$("#questions").append(qrow);
				console.log("append answer end");
				console.log("calling answer start");
				fillAnswer(data[index].questionId);
				console.log("calling answer end");
			}
		});
		
		function fillAnswer(qID){
			$.get("rest/answers/"+qID, function(data1){
				var ansTemplate = $("#answerTemplate").html();
				console.log(ansTemplate);
				var index1	
				for(index1=0;index1<data1.length;index1++) {
					var arow = ansTemplate.replace("answerTextV",data1[index1].text).replace("auserNameV",data1[index1].username).replace("upVotesV",data1[index1].upvoteCount)
					$("#questions").append("<tr>" + arow +"</tr>");
				}
			});
		};
		
		$("#sampleQuestions").on('click','a', function(e){
			var qID = $( this ).attr('id');
			console.log(qID);
			$("#questions").empty();
			fillQuestion(qID);
		});
		
		function fillQuestion(qID){
		$.get("rest/questions/"+qID, function(data){
			var rowTemplate = $("#questionTemplate").html();
			console.log(rowTemplate);
			var index;
			for(index=0; index<1;index++){
				var qrow =  rowTemplate.replace("questionTitleV",data.title).replace("questionTextV",data.text).replace("quserNameV",data.username).replace("viewCountsV",data.viewsCount)
				console.log("append answer start");
				$("#questions").append(qrow);
				console.log("append answer end");
				console.log("calling answer start");
				fillAnswer(data.questionId);
				console.log("calling answer end");
			}
		});
		};
    });
		
    </script>
</head>

<body style="font-family: 'Nunito', sans-serif; color: #384047;">
    
	<div  class="nav">
      <ul>
        <li> <a  class="active"  href="#home">Home</a></li>
        <li> <a  href="#contact">Contact</a></li>
        <li> <a  href="#about">About</a></li>
        <li  style="float:right"><a  href="signup.jsp">SignUp</a></li>
        <li  style="float:right"><a  href="#login">Login</a></li>
      </ul>
    </div>
	<div>
      <h1 align="center" >Welcome To CMAD BLOG</h1>
	  <table  style="width: 80%;"  align="center"  border="0">
        <tbody>
          <tr>
            <td  style="width: 70%; vertical-align: top; border-right: 20px solid transparent;">
				<table id="questions" style="width: 100%"  border="0">

				</table>
			</td>
            <td  style="width: 100%; text-align: center; vertical-align: center;">
              <h3 style = "background: #324c97; color:#fff;">Popular Questions</h3>
              <table id="sampleQuestions" style="width: 100%;  border: 2px solid #324c97;" >
              	
              </table>
            </td>
		  </tr>
		</tbody>
      </table>
	</div>
	<div id="templates" style="display: none;">
		<table id="sampleQuestionTemp">
			<tr><td><a id style="font-weight: 700; font-size: 16px; display: block; cursor: pointer; color: #39739d; margin: 3px 0; font-family: Calibri; padding: 6px;">questionTitle </a>
				</td>
			</tr>
		</table>
        <table id="questionTemplate">
			<tr>
                <td colspan="4">
                    <h3><span  id="questionTitle"  style="font-weight: bold; font-family: Calibri;">questionTitleV</span></h3>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div id="questionText" style="font-family: Calibri;">questionTextV</div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br>
                </td>
                <td style="width: 25%; text-align: right;"><span id="quserName">quserNameV</span>
                </td>
                <td style="width: 25%; text-align: center;"> <span id="viewCounts">viewCountsV</span>
                </td>
				
            </tr>
			<tr>
				<td colspan="4"><span> <hr style="display: block;"> </span></td>
			</tr>
            <tr>
                <table id="answerTemplate">
                    <tr>
                        <td colspan="4">
                            <div id="answerText" style="font-family: Calibri;">
                                answerTextV
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <br>
                        </td>
                        <td style="text-align: right;"><span id="auserName">auserNameV<span><br>
                    </td>
                    <td style="text-align: center;"><span id="upvoteCounts">upVotesV</span>
                            <br>
                        </td>
                    </tr>
                </table>
            </tr>
        </table>
    </div>
</body>

</html>