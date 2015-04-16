package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Browse
 */
public class Browse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Browse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			browse(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "/browseresult.jsp";
		
		// This is to check if the 		
		String Bgrade = request.getParameter("grade");
		if (Bgrade == null) Bgrade = "";
		
		String classroom = request.getParameter("classroom");
		if (classroom == null) classroom = "";
		
		String topic = request.getParameter("topic");
		if (topic == null) topic = "";
		
		String school = request.getParameter("school");
		if (school == null) school = "";
		
		String contentarea = request.getParameter("contentarea");
		if (contentarea == null) contentarea  = "";
		
		//To Call SearchThread() method depending on option clicked 
		if (Bgrade != "")
		{
			try {
				SerachThreads(request, response, 1, Bgrade);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (classroom != "")
		{
			try {
				SerachThreads(request, response, 2, classroom);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (topic != "")
		{
			try {
				SerachThreads(request, response, 4, topic);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (school != "")
		{
			try {
				SerachThreads(request, response, 3, school);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (contentarea != "")
		{
			try {
				SerachThreads(request, response, 5, contentarea);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void SerachThreads(HttpServletRequest request, HttpServletResponse response, int option, String Category) throws ServletException, IOException, SQLException
	{
		HttpSession session = request.getSession(false);
		
		String SQuery = ""; 
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		System.out.println("I am retreiving Threads for :"+Category);
		
		switch(option)
		{
				case 1 :	SQuery = "select distinct(threadfocus) from thread_note where projectid in (select projectid from project_grade where grade = ?)"; 
							break;
							
									// Need to write query for classroom
				case 2 :	SQuery = "select distinct(threadfocus) from thread_note where projectid in (select projectid from project_grade where grade = ?)"; 
							break;
				
				case 3 :	SQuery = "select distinct(threadfocus) from thread_note where projectid = (select projectid from project where school = ?);"; 
							break;
	
				case 4 :	SQuery = "select distinct(threadfocus) from thread_note where projectid = (select projectid from project where  projectname = ?)"; 
							break;
	
				case 5 :	SQuery = "select distinct(threadfocus) from thread_note where projectid = (select projectid from project where contentarea = ?)";
							break;
	
				default : 	break;
								
		}
			
		try
		{
			connection = getConnectionToYourDB();
			statement = connection.prepareStatement(SQuery, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.setString(1,Category);
			rs = statement.executeQuery();
			
		}
		catch(SQLException e)
		{
				e.printStackTrace();
		}
				
		if (rs != null)
		{
		       ArrayList<String> BgThread = new ArrayList<String>();
		
		       try 
		       {	
		    	   int j = 0;
		     	   while(rs.next())
		    	   {
		     		   		j++;
		   					BgThread.add(rs.getString("threadfocus"));
		    				System.out.println(j+rs.getString("threadfocus"));
		   	       }
		           //classroom.add(rs2.getString("title"));
						     
		       	} 
		       	catch (SQLException e) {
		   		// TODO Auto-generated catch block
		       		e.printStackTrace();
		       	}

				session.setAttribute("BgThread", BgThread);
			}
		}



	
	protected void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(false);
			
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
			      
			    	   //for (int i = 1;  rs1.next() && i <= rsSize(rs1); i++)
			    	   {int i = 1;
			    		  while( rs1.next())
					       {
			    		  //System.out.println("I am inside Query1 loop!!");
			    		   topic.add(rs1.getString("projectname"));
					       school.add(rs1.getString("school"));
					       contentarea.add(rs1.getString("contentarea"));
					       /*System.out.println("p:"+i+rs1.getString(1));
					       System.out.println("s"+i+rs1.getString("school"));
					       System.out.println("c"+i+rs1.getString("contentarea"));*/
					       i++;
					       }
				    }
			       }
			      	catch (SQLException e) {
			       		// TODO Auto-generated catch block
			       		e.printStackTrace();
			       	}
							    
				/*request.setAttribute("topic", topic);
				request.setAttribute("school", school);
				request.setAttribute("contentarea", contentarea);*/

				session.setAttribute("topic", topic);
				session.setAttribute("school", school);
				session.setAttribute("contentarea", contentarea);
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
					    				/*System.out.println(j+rs2.getString("title"));*/
					       	       }
					               //classroom.add(rs2.getString("title"));
					     
			       	} 
			       	catch (SQLException e) {
			       		// TODO Auto-generated catch block
			       		e.printStackTrace();
			       	}
			       
					      
			      /* request.setAttribute("classroom", classroom);*/
			       session.setAttribute("classroom", classroom);

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
				
			     /*  request.setAttribute("grade", grade);*/
			       
			       session.setAttribute("grade", grade);
      
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
				 	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","root");
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
	
//###############################################################################################################################################



}
