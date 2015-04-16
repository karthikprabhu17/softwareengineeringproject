package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 * 
 * @author Vinay Yachawad
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	}

	/**
	 * Validation of form using Username and password.
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		System.out.print(sex);
		String userID = "hi";
		String password = "hi";
		String msg = "";
		String loggedIn = "";
		if (sex == null) {
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			msg = "Please Select User";
			request.setAttribute("loggedIn", "false");
			request.setAttribute("msg", msg);
			requestDispatcher.include(request, response);
		} else if (LoginDao.validate(user, pwd)
				&& ("teacher").equalsIgnoreCase(sex)) {
			
			// syed
			
			HttpSession session = request.getSession(true);
			loggedIn = "true";
			Browse b= new Browse();
			try {
				b.browse(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("user", user);
			session.setAttribute("loggedIn", loggedIn);
			response.sendRedirect("index.jsp");
		} else if (LoginDao.validate_Students(user, pwd)
				&& ("student").equalsIgnoreCase(sex)) {

			HttpSession session = request.getSession(true);
			loggedIn = "true";
			session.setAttribute("user", user);
			session.setAttribute("loggedIn", loggedIn);
			response.sendRedirect("index_Student.jsp");
		} else if (user.length() == 0 || pwd.length() == 0) {

			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			msg = "Please enter the credentials.";
			request.setAttribute("msg", msg);
			request.setAttribute("loggedIn", "false");
			requestDispatcher.include(request, response);
		} else {
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			msg = "Either user name or password is wrong.";
			request.setAttribute("loggedIn", "false");
			request.setAttribute("msg", msg);
			requestDispatcher.include(request, response);

		}

	}

	/**
	 * logout session.
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null)
			try {
				session.invalidate();
				RequestDispatcher requestDispatcher = getServletContext()
						.getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				request.setAttribute("msg", "You are Successfully logged out.");
				
				requestDispatcher.include(request, response);
			} catch (Exception e) {

			}

	}
	
protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		
		//	String url = null;
		//	/String msg = null;
			
		//	int userId = -1;
			
			response.setContentType("text/html");

			
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			ResultSet rs3 = null;
		
			String Query1 = "select projectname, school, contentarea from project";
			String Query2 = "select title from group_table";
			String Query3 = "select grade from project_grade";
		
			
			Connection connection = null;
			
			try
			{
				connection = getConnectionToYourDB();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs1 = statement.executeQuery(Query1);
				
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs2 = statement.executeQuery(Query2);
				
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs3 = statement.executeQuery(Query3);
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			if (rs1 != null)
			{
			       ArrayList<String> topic = new ArrayList<String>();
			       ArrayList<String> school = new ArrayList<String>();
			       ArrayList<String> contentarea = new ArrayList<String>();

	    		   System.out.println("I am outside Query1 loop!!");
			       try
			       {
			      
			    	   //for (int i = 1;  rs1.next() &&i <= rsSize(rs1); i++)
			    	   {int i = 1;
			    		  while( rs1.next())
					       {
			    		   System.out.println("I am inside Query1 loop!!");
			    		   topic.add(rs1.getString("projectname"));
					       school.add(rs1.getString("school"));
					       contentarea.add(rs1.getString("contentarea"));
					       System.out.println("p:"+i+rs1.getString(1));
					       System.out.println("s"+i+rs1.getString("school"));
					       System.out.println("c"+i+rs1.getString("contentarea"));
					       i++;
					       }
				    }
			       }
			      	catch (SQLException e) {
			       		// TODO Auto-generated catch block
			       		e.printStackTrace();
			       	}
							    
				request.setAttribute("topic", topic);
				request.setAttribute("school", school);
				request.setAttribute("contentarea", contentarea);

			}
			
			if (rs2 != null)
			{	
				  System.out.println("I am retriving Classroom!!");
			       ArrayList<String> classroom = new ArrayList<String>();
			       try 
			       {	int j = 0;
			    	   while(rs2.next())
			    	   {j++;
			    	   					classroom.add(rs2.getString("title"));
					    				System.out.println(j+rs2.getString("title"));
					       	       }
					               //classroom.add(rs2.getString("title"));
					     
			       	} 
			       	catch (SQLException e) {
			       		// TODO Auto-generated catch block
			       		e.printStackTrace();
			       	}
			       
					      
			       request.setAttribute("classroom", classroom);

			}
		
					
			if (rs3 != null)
			{
				  System.out.println("I am retriving Grade!!");
			      ArrayList<String> grade = new ArrayList<String>();
					       	
			       try
			       {
			    	  while (rs3.next())
		       	       {
			       	    	   grade.add(rs3.getString("grade"));
			       	    	   System.out.println(rs3.getString("grade"));
			               
		       	       }
			        }
			        catch (SQLException e) {
			       		// TODO Auto-generated catch block
			       		e.printStackTrace();
			       	}
				
			       request.setAttribute("grade", grade);
      
			}
			try
			{
				DB_Close(connection);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
			//dispatch.forward(request, response);
		
				
	}

	/*/###############################################################################################################################################
		This method is to get the database connection.
		*/
	
	protected static Connection getConnectionToYourDB() throws SQLException
	{
		
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			}
	
			catch (ClassNotFoundException e) {
				 
					e.printStackTrace();
			}
			 Connection connection = null;
			 //System.out.println("mySQL JDBC Driver Registered!");
			 try{
				 	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","1234");
			 	} catch (SQLException e) {
	
			 		System.out.println("Connection Failed! Check output console");
			 		e.printStackTrace();
			 		return null;
			 	}
	
			 if (connection != null) {
				 //System.out.println("You made it, take control your database now!");
				 return connection;
			 	}
			 else {
				// System.out.println("Failed to make connection!");
				 return null;
			 	}
	
	}
	
//##############################################################################################################################################

	//Closes the DB connection
	public static void DB_Close(Connection connection) throws Throwable
	{
			System.out.println("DB_closed activated!!");
			try
			{
				if(connection!=null){
					connection.close();
				}
			}
			catch(SQLException e){
				
				System.out.println(e);
			}
				
	}


//################################################################################################################################################################
	public static int rsSize(ResultSet rs)
	{
		int rssize = 0;
		try {
			if (rs.last())
			{
				rssize = rs.getRow();
				//System.out.println("Here is the problem!!!");
				//rs.beforeFirst();
				
			}
			
			System.out.println("size of the result set:"+rssize);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rssize;
	
	}

}