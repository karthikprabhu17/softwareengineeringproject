<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import ="java.io.*" %>
<%@ page import ="com.mysql.jdbc.*" %>
<%@include file="DbConnection.jsp" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.text.ParseException" %>
<%@ page import ="java.text.SimpleDateFormat;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
	private String date_value(String DateString)
	{
		String date_s = DateString;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date;
		try
		{
			date = dt.parse(date_s);
			SimpleDateFormat output_date = new SimpleDateFormat("dd-MMMMM-YYYY hh:mm a");
			return output_date.format(date);
		}
		catch(ParseException e)
		{
			System.out.println(e);
		}
		
		return null;	
	}
	
	%>
	
	<%
	String threadid= (String)request.getParameter("threadid");
	%>
	
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js "></script>

<script>
		function loadcomments()
		{
			var x= document.getElementsByName("cname");
			var type = "ctype";
			
			
			for (var i=0,len=x.length;i<len;i++)
			{
			var note=null;
			var noteid=null;
			var cdiv=null;
			note = x[i];
			noteid = note.getAttribute("id");
		//	alert("noteid:"+noteid);
			cdiv= "C" + noteid;

			$('#'+cdiv).empty();
		
			
			}
			
			

			for (var i=0,len=x.length;i<len;i++)
				{
				var note=null;
				var noteid=null;
				var cdiv=null;
				note = x[i];
				noteid = note.getAttribute("id");
			//	alert("noteid:"+noteid);
				cdiv= "C" + noteid;

				//var input = document.getElementById(cdiv);
				
				$.post('ThreadPage',{noteid:noteid,ctype:type},function(responseText) {
					var obj = eval ("(" + responseText + ")");
					var notecount= obj.note.length;
					for(var j=0;j<notecount;j++)
						{
					var noteid = "C"+obj.note[j].noteid;
					var commenttext = obj.note[j].commenttext;
					var creation_date = obj.note[j].creationdate;
					var username = obj.note[j].username;
				
					$('#'+noteid).append("<div id='commentbox'>"+"<div><u><b>"+username+" :</b></u> "+commenttext+"</div></br>"+"<div>commented on : "+creation_date+"</div>"+"</div>");
					$('#'+noteid).append("</br>");
						}
		        });
				
				}
			
		}
</script>

	<script type="text/javascript">	
 	
	$(".comment").keyup(function( event ) {
  if ( event.which == 13 ) {
	  var $input = $( this );
	  var i= $input.attr("id");
	  var commentvalue= document.getElementById(i).value.replace(/(\r\n|\n|\r)/gm,"");
	  if (commentvalue.length>0)
		  {
	  var threadname= document.getElementById("threadname").innerHTML;
           $.post('ThreadPage',{noteid:i,comment:commentvalue,tname:threadname},function(responseText) { 
        	   document.getElementById(i).value="";
        	   loadcomments();
        	 
              });
		  }
	}
	});
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<%
			String username= (String)session.getAttribute("user");
			System.out.println(username);
		ResultSet rs1=null;
		
		String sql1 = "select a.threadfocus,b.notetitle,b.notecontent,b.createtime,b.noteid from thread_note a,note_table b where a.noteid=b.noteid and a.threadfocus=? order by createtime desc;";
		
		try{	
		p_sql = con.prepareStatement(sql1);
		p_sql.setString(1, threadid);
		rs1 = p_sql.executeQuery();
		%>
		
		<%
			out.println("<div id=\"threaddiv\">");
			int count=1;
			while( rs1.next() ){
				if (count==1)
				{
					out.println("<div id=\"threadname\">"+rs1.getString("threadfocus")+"</div>");
					
				}
				
				out.println("<ul class=\"threadn\">");			
				
					out.println("<li>");
					out.println("<div id=\"ntitle\"><b>"+rs1.getString("notetitle")+"</b></div>");
							out.println("<ul>");
								out.println("<li class=\"lin\">");
											out.println("<p>"+rs1.getString("notecontent")+"</p>");
											out.println("<p class=\"ntime\">Note was created on: "+date_value(rs1.getString("createtime"))+"</p>");
											out.println("<b>Comments</b></br>");
											%>
											</br><div id=<%="C"+rs1.getString(5)%>></div></br>
											<textarea class="comment" name="cname" id=<%=rs1.getString("noteid")%> placeholder="Write a comment..." rows="4" cols="60" ></textarea></br></br>
											<h3>Views(31) &nbsp Comments(13) &nbsp recommendations(7)  &nbsp follows(5)</h3>										
											<%
								out.println("</li>");
							out.println("</ul>");
							
				out.println("</li>");
				
				out.println("</ul>");
			
				count++;
		%>
			
			<%
		
			}
		
			
			
			%>
			
		<%

		}
			catch(Exception e){e.printStackTrace();}
			finally{
			if(rs1!=null) rs1.close();
			if(s!=null) s.close();
			if(con!=null) con.close();
			}

	%>

<script type="text/javascript">	
			loadcomments();
</script>

</body>
</html>