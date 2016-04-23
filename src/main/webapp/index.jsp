<html>

<head>
	<title>Blog</title>
	<link rel="stylesheet" type="text/css" href="css/myStyle.css">
    <script src="scripts/jquery-2.1.3.js"></script>
    <script>
    var dataBackup = null;
        $(document).ready(function() {
            $.get("rest/questions", function(data) {
                dataBackup = data;
                var rowTemplate = $("#questionTemplate").html();
                console.log(rowTemplate);
                var index;
                for (index = 0; index < dataBackup.length; index++) {
                    //for(index in data){
                    $.get("rest/answers/" +dataBackup[index].questionId, function(data1) {
                        var index = data1[0].questionId;
                        var qObject = null;
                        for(var i=0; i<dataBackup.length; i++) {
                            if(dataBackup[i].questionId === index) {
                                qObject = dataBackup[i];
                                break;
                            }
                        }
                        var qrow = rowTemplate.replace("questionTitleV", qObject.title).replace("questionTextV", qObject.text).replace("quserNameV", qObject.username).replace("viewCountsV", qObject.viewsCount)
                        console.log("append answer start");
                        //$("#questions").append(qrow);
                        console.log("append answer end");
                        console.log("calling answer start");
                        var ansTemplate = $("#answerTemplate").html();
                        console.log(ansTemplate);
                        var index1
						var arow =qrow;
                        for (index1 = 0; index1 < data1.length; index1++) {
                            
							arow = arow + ansTemplate.replace("answerTextV", data1[index1].text).replace("auserNameV", data1[index1].username).replace("upVotesV", data1[index1].upvoteCount)
								
						  //  $("#questions").append("<tr>" + arow + "</tr>");
                        }
						
						$("#questions").append(arow);
                    });
                    console.log("calling answer end");
                }
            });
        });
    </script>
</head>

<body>
    
	<div  class="nav">
      <ul>
        <li> <a  class="active"  href="#home">Home</a></li>
        <li> <a  href="#contact">Contact</a></li>
        <li> <a  href="#about">About</a></li>
        <li  style="float:right"><a  href="#signup">SignUp</a></li>
        <li  style="float:right"><a  href="#login">Login</a></li>
      </ul>
    </div>
	<div>
      <h1 align="center">Welcome To CMAD BLOG</h1>
	  <table  style="width: 80%;"  align="center"  border="0">
        <tbody>
          <tr>
            <td  style="width: 70%;">
				<table id="questions" style="width: 100%"  border="0">

				</table>
			</td>
            <td  style="width: 80%; text-align: left; vertical-align: top;">
              <h3>Popular Questions</h3>
              <h3> </h3>
            </td>
		  </tr>
		</tbody>
      </table>
	</div>
	<div id="templates" style="display: none;">
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
                <td style="width: 25%; text-align: right;"> <span id="viewCounts">viewCountsV</span>
                </td>
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
                    <td style="text-align: right;"><span id="upvoteCounts">upVotesV</span>
                            <br>
                        </td>
                    </tr>
                </table>
            </tr>
        </table>
    </div>
</body>

</html>