package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;              
import org.json.JSONObject;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ThreadPage
 */
@WebServlet("/ThreadPage")
public class ThreadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String noteid = (String)request.getParameter("noteid");
		String comment = (String)request.getParameter("comment");
		String tname = (String)request.getParameter("tname");
		String ctype = (String)request.getParameter("ctype");
		HttpSession session=null;
		session = request.getSession(false);
		String username= (String)session.getAttribute("user");
		if (ctype==null) ctype="";
		System.out.println("reached man your noteid is"+noteid);
		
		
		  java.sql.Connection con=null;
		  java.sql.PreparedStatement s;
		  ResultSet g=null;
		 
	if (ctype=="")
	{
		 try
		 {
		  int noteint =  Integer.parseInt(noteid);
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","root");
		  String s1= "insert into city_note_comments(threadfocus,noteid,commenttext,username) values (?,?,?,?)";
		  s= con.prepareStatement(s1);
		  s.setString(1, tname);
		  s.setInt(2, noteint);
		  s.setString(3, comment);
		  s.setString(4, username);
		  s.executeUpdate();

		 }
		 
		  catch(Exception ex)
			 {
			 	
			 	ex.printStackTrace();
			 }
		 finally
		 {
			 try
			 {
				 con.close();
			 }
			
			 catch(Exception ex)
			 {
			 	
			 	ex.printStackTrace();
			 }
		 }
	}
	else
	{
		 try
		 {
		  int noteint =  Integer.parseInt(noteid);
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","root");
		  String s1= "select commentid,commenttext,username,DATE_FORMAT(creationdate, '%d-%M-%Y %r') \"creationdate\",username from city_note_comments where noteid=? order by creationdate";
		  s= con.prepareStatement(s1);
		  s.setInt(1, noteint);
		  g=s.executeQuery();

		  PrintWriter out = response.getWriter();
		  StringBuffer sb=new StringBuffer();
		  sb.append("<?xml version='1.0' encoding='ISO-8859-1'?>\n");
		  JSONObject mobj = null;
		  JSONObject obj = null;
	
		  JSONArray list = new JSONArray();
		  while (g.next())
		  {
			  obj = new JSONObject();		
			  mobj = new JSONObject();
	          obj.put("noteid", noteid);
	          obj.put("commenttext",g.getString(2));
	          obj.put("creationdate",g.getString(4));
	          obj.put("username",g.getString(5));
	          list.put(obj);
	          mobj.put("note",list);	 
		  }
		  out.println(mobj);  
		  out.flush();
		//  out.println("</div>");
		  out.close();
		 }
		  
		 
		  catch(Exception ex)
			 {
			 	
			 	ex.printStackTrace();
			 }
		 
		 
		 finally
		 {
			 try
			 {
				 con.close();
			 }
			
			 catch(Exception ex)
			 {
			 	
			 	ex.printStackTrace();
			 }
		 }
		 
	}
	}
		 
		 
		
	}
