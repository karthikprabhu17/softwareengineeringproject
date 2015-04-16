
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Study Book</title>
<meta name="keywords" content="" />
<meta name="Premium Series" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<!-- start header -->
	<div id="header">
		<div id="logo">
			<h1>
				<a href="#"><span>Study</span>Book</a>
			</h1>
			<p>Designed By Connecting Idea Threads of Youth</p>
		</div>
		<div id="menu">
			<ul id="main">

				<li><a href="#">Login to access Study Book</a></li>
			</ul>
			<ul id="feed">


			</ul>
		</div>

	</div>
	<!-- end header -->
	
	
	<div id="wrapper">
		<!-- start page -->
		<div id="page">

			<!-- start content -->
			
				<div class="flower">
					<img src="images/studentsnote.jpg" alt="" width="510" height="180" />
				</div>
				
					<h2>Login</h2>
<h3><%
		String msg = (String) request.getAttribute("msg");
		if (msg == null)
			msg = "";
		out.print(msg);
	%></h3>
					<form action="LoginServlet" method="post">
						<input type="radio" name="sex" value="teacher">Teacher
						<input type="radio" name="sex" value="student">Student
					

					<p>
						<input type="text" placeholder="Username" name="user">
					</p>
					<p>
						<input type="password" placeholder="Password" name="pwd">
					</p>
					
					<button type="submit" value="Login">Submit</button>
				</form>

			<!-- end content -->
			<!-- start sidebars -->

			<!-- end sidebars -->
			<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end page -->
	</div>
	<div id="footer">
		<p class="copyright">
			&copy;&nbsp;&nbsp;2014 All Rights Reserved &nbsp;&bull;&nbsp; Design
			by Team 6</a>.
		</p>
		<p class="link">
			<a href="#">Privacy Policy</a>&nbsp;&#8226;&nbsp;<a href="#">Terms
				of Use</a>
		</p>
	</div>
</html>
