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
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />

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
<script type="text/javascript">


</script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.min.js "></script>
<body onload="ticker()">

	<script type="text/javascript">
	
	$(document).ready(function(){
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

		function show_data() {
			$('#content').load('ThreadPage.jsp #threaddiv');
		}
	</script>
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
				<li class="current_page_item"><a href="#">Homepage</a></li>
				
				
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
						<h2>Student Profile</h2>
						<ul>
							<li><a href="#">Vinay Yachawad</a></li>
							<li><div class="flower">
									<img src="images/Vinay.jpg" alt="" width="=20" height="70" class="avatar"/>
								</div></li>
							<li><a href="#">Edit Profile</a></li>


						</ul>
					</li>
					<li>
						<h2>Activities</h2>
						<ul>
							<li><div class="text_container hidden"> <a> Browse</a> 
							<div>
									<ul>
									
										<li>
											<div class="showhim">Grade
											<%!int category = 0;
										    	String categ_value = null; %>
											<%
											 ResultSet rs1=null;
											String query1="select grade, school from project_grade pg, project p where pg.projectid = p.idproject";
											try{
													 s = con.createStatement();
													rs1 = s.executeQuery(query1);%>
											
													<div class="showOnHover">	
											        <ul id = "myid">
											     			<% while(rs1.next()){ %>
																<a><li> <%=rs1.getString(1)%></li> </a><%}%>
													</ul>
													</div>
											</div>
											<%}catch(Exception e){
												e.printStackTrace();
											  }
											  finally{
													 if(rs1!=null) rs1.close();
													
											  }%>
										</li>		
									<li>
											<div class="showhim">School
										
											
										</li>
											<li>Content Area</li>
										<li>Topics</li>
										<li>Class Room</li>				
									</ul>
								</div>
							</div></li>
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
						ResultSet rs = null;

						String sql = "select * from thread_note a, note_table b where a.noteid=b.noteid order by b.createtime desc limit 60;";

						try {
							s = con.createStatement();
							rs = s.executeQuery(sql);
					%>

					<%
					out.println("<div class=\"ticker\">");
					out.println("<h3>Latest News</h3>");
							out.println("<ul id=\"ticker\">");
							out.println("<ul>");
							while (rs.next()) {
								String threadid = rs.getString("threadfocus");

								String thread_url = "http://localhost:8080/SE/ThreadPage.jsp?threadid="
										+ threadid;
					%>
					<ul>
						<li><a href=# 
							style="text-decoration: none" onclick="show_data()"> <b>
									<u><%=rs.getString("threadfocus")%>
							</b></u> <br>
							 The Thread has a new note with title
									"<%=rs.getString("notetitle")%>" <br>Note was created on
									<%=date_value(rs.getString("createtime"))%> <b>
									<p>
										Views(31) Comments(5)
											Recommendations(20) Follows(4) 
									</p>
							</b>
						</a></li>
					</ul>
					<%
						}
					%>

					<%
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs != null)
								rs.close();
							if (s != null)
								s.close();
							if (con != null)
								con.close();
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
</html>
