<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.mysql.jdbc.*"%>
<%@include file="DbConnection.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat;"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Study Book</title>
<meta name="keywords" content="" />
<meta name="Premium Series" content="" />
<link href="./default.css" rel="stylesheet" type="text/css"
	media="screen" />

<%!private String date_value(String DateString) {
		String date_s = DateString;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date;
		try {
			date = dt.parse(date_s);
			SimpleDateFormat output_date = new SimpleDateFormat(
					"dd-MMMMM-YYYY hh:mm a");
			return output_date.format(date);
		} catch (ParseException e) {
			System.out.println(e);
		}

		return null;
	}%>






</head>


<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.min.js "></script>



<!-- syed -->



<script type="text/javascript">

function invokeServlet()
{
  // form a URL with the servlet name to be invoked
  var URL = 'http://localhost:8080/citi/Browse';

  // This line will inkove a servlet and reload your page
  location.href = URL;
}

	$(document).ready(function() {
		$('.text_container').addClass("hidden");

		$('.text_container').click(function() {
			var $this = $(this);

			if ($this.hasClass("hidden")) {
				$(this).removeClass("hidden").addClass("visible");

			} else {
				$(this).removeClass("visible").addClass("hidden");
			}
		});
	});

	function ticker() {
		$('#ticker li:first').slideUp(function() {
			$(this).appendTo($('#ticker')).slideDown();
		});
	}

	var timer = setInterval(ticker, 1000);

	$(document).ready(function() {
		$("#ticker").mouseover(function() {
			clearInterval(timer);
		});
		$("#ticker").mouseout(function() {
			timer = setInterval(ticker, 1000);
		});
	});

	$(document).ready(function() {
		$("#notecontent").click(function() {
			$("#notecontent").slideToggle("slow");
		});
	});
	
	function show_data(id) {
		var threadn = id;
		$('#content').load('ThreadPage.jsp', {
			threadid : threadn
		}, function(responseTxt, statusTxt, xhr) {

		});
	}
	
	

</script>
<body onload="ticker()">


	<%
		String loggedIn = (String) session.getAttribute("loggedIn");
		if (loggedIn == null || loggedIn == "false")
			out.print("<h1>" + "You are not logged in" + "</h1>");
		else {
	%>






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
				<li class="current_page_item"><a href="./index.jsp">Homepage</a></li>

				<li><a href="Upload.jsp">Create Communities</a></li>
				<li><a href="#">Communities</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
			<ul id="feed">

				<li><a href="LoginServlet">Logout</a></li>
			</ul>
		</div>

	</div>
	<!-- end header -->
	<div id="wrapper">
		<!-- start page -->
		<div id="page">
			<div id="sidebar1" class="sidebar">
				<ul>
					<li>
						<h2>Teacher Profile</h2>
						<ul>
							<li><a href="#">Vinay Yachawad</a></li>
							<li><div class="flower">
									<img src="images/Vinay.jpg" alt="" width="=20" height="70"
										class="avatar" />
								</div></li>
							<li><a href="#">Edit Profile</a></li>


						</ul>
					</li>
					<li>

						<h2>Activities</h2>
						<ul >

							<ul>
								<li><div class="text_container hidden">
										Browse
										<div>
											<ul>
												<li>
													<div class="showhim">grade
														<%
																//	ArrayList<String> grade = (ArrayList<String>) request.getAttribute("grade");
														ArrayList<String> grade = (ArrayList<String>) session.getAttribute("grade");
														%>		
												
														<div class="showOnHover">
															<ul>
																<%
																				if (grade != null) {
																						for (String g : grade) {
																 %>
																<li><a href="Browse?grade=<%=g%>"><%=g%></a></li>
																<%
																				}
																					}
																			%>
															</ul>
														</div>
													</div>
												</li>
												<li>
													<div class="showhim">
														Class Room
														<%
																	/* ArrayList<String> classroom = (ArrayList<String>) request.getAttribute("classroom"); */
																	ArrayList<String> classroom = (ArrayList<String>) session.getAttribute("classroom");
														%>
														<div class="showOnHover">
															<ul>
																<%
																				if (classroom != null) {
																						for (String c : classroom) {
																			%>
																<li><a href="Browse?classroom=<%=c%>"><%=c%></a></li>
																<%
																				}
																					}
																			%>
															</ul>
														</div>
													</div>
												</li>
												<li>
													<div class="showhim">
														School
														<%
																	/* ArrayList<String> school = (ArrayList<String>) request.getAttribute("school"); */
																	ArrayList<String> school = (ArrayList<String>) session.getAttribute("school");
																	%>
														<div class="showOnHover">
															<ul>
																<%
																				if (school != null) {
																						for (String sc : school) {
																			%>
																<li><a href="Browse?school=<%=sc%>"><%=sc%></a></li>
																<%
																				}
																					}
																			%>
															</ul>
														</div>
													</div>
												</li>
												<li>
													<div class="showhim">
														Topic
														<% 
														/* ArrayList<String> topic = (ArrayList<String>) request.getAttribute("topic"); */
														ArrayList<String> topic = (ArrayList<String>) session.getAttribute("topic");
														%>
														<div class="showOnHover">
															<ul>
																<%
																				if (topic != null) {
																						for (String t : topic) {
																			%>
																<li><a href="Browse?topic=<%=t%>"><%=t%></a></li>
																<%
																				}
																					}
																			%>
															</ul>
														</div>
													</div>
												</li>
												<li>
													<div class="showhim">
														Content Area
														<%
														/* 			ArrayList<String> contentarea = (ArrayList<String>) request.getAttribute("contentarea"); */
																	ArrayList<String> contentarea = (ArrayList<String>) session.getAttribute("contentarea");
														%>
														<div class="showOnHover">
															<ul>
																<%if (contentarea != null) 
																{
																	for (String ca : contentarea) {%>
																		<li><a href="Browse?contentarea=<%=ca%>"><%=ca%></a></li>
																		<%}	
																}%>
															</ul>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div></li>
							</ul>
							<li><a href="#"> Follow</a></li>
							<li><a href="#"> Discover</a></li>
							<li><a href="#"> Collect and recommend</a></li>
							<li><a href="#"> Adopt</a></li>

						</ul>
					</li>
					<li>
						<h2>Communities</h2>
						<ul>
							<li><a href="#">Community 1</a></li>
							<li><a href="#">Community 2</a></li>
							<li><a href="#">Community 3</a></li>
							<li><a href="#">Community 4</a></li>
						</ul>
					</li>

				</ul>
			</div>
			<!-- start content -->
			<div id="content">
				<div class="flower">
					<img src="images/studentsnote.jpg" alt="" width="510" height="180" />
				</div>

			
					<div class="post">
					<%
						 ArrayList<String> threadarray= new ArrayList<String>();
						 threadarray = (ArrayList)session.getAttribute("BgThread");
						 ResultSet rs = null; 
						 
						 out.println("<div class=\"ticker1\">");
							out.println("<h3>Browse Results</h3>");
							out.println("<ul id=\"ticker1\">");
							out.println("<ul>");
						
					
					   for(String i: threadarray)
					   
					   {
						
							String threadfocus= i;
							System.out.println(i);
	
								String sql = "select threadfocus,notetitle,createtime from thread_note a, note_table b where a.noteid=b.noteid and a.threadfocus=? order by createtime desc limit 1;";
	
								try {
											browse_sql = con.prepareStatement(sql);
											browse_sql.setString(1, i);
											rs = browse_sql.executeQuery();
							
											
											if(rs.next())
												
											{
												String threadid = rs.getString("threadfocus");
												%>
													
													<li><a href=# id=<%=rs.getString("threadfocus")%>
														style="text-decoration: none" onclick="show_data(id)"> <b>
																<u><%=rs.getString("threadfocus")%>
														</b></u> <br> The Thread has a new note with title "<%=rs.getString("notetitle")%>"
															<br>Note was created on <%=date_value(rs.getString("createtime"))%>
															<b>
														</b>
													</a></li>
											
											<%
											}
									} 
									catch (Exception e) 
									{
										e.printStackTrace();
									} 
							
					   			
					   }
					%>
				</div>
				
			</div>

		</div>
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
	<%} %>
</body>
</html>
