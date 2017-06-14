


<%@taglib prefix="junjun"
	uri="http://www.junjun-dachi.org/spring/mvc/token"%>

<div id="div-msg" width="100%"
	style="font-family: Verdana, Geneva, sans-serif; font-size: 30px; text-align: center; padding-top: 100px;"></div>


<form id="form-test"
	action="${pageContext.request.contextPath}/test.htm" method="post">
	<junjun:token />
	
</form>

<script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>

<script>

	$(document).ready(function() {

						var token = $('#token').val();

						$('#div-msg').append('<p>System has generated token :' + token + '.</p>');

						$('#div-msg').append('<p> System is going to submit form twice with AJAX call.</p>');
						$('#div-msg').append('<p> And one of the requests should fail.</p>');
						
						var url = ${pageContext.request.contextPath} + '/test.htm';
					    $.ajax({
					           type: 'POST',
					           url: 'test.htm',
					           data: $('#form-test').serialize(),
					           success: function(data)
					           {
					               console.log(data);
					               $('#div-msg').append('<p> Response from 1st request ' + data + '.</p>');
					           },
					           error: function(msg){
					        	   console.log(msg);
					           }
					    });
					    
					    $.ajax({
					           type: 'POST',
					           url: 'test.htm',
					           data: $('#form-test').serialize(),
					           success: function(data)
					           {
					               console.log(data);
					               $('#div-msg').append('<p> Respones from 2nd request ' + data + '.</p>');
					           },
					           error: function(msg){
					        	   console.log(msg);
					           }
					    });

					});
</script>