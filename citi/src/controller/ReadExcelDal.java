package controller;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class ReadExcelDal
{


	 public  void validate( String user,String pass)
	 {          
	        boolean status = false;  
	        Connection conn = null;  
	        PreparedStatement pst = null; 
	        int rs; 
	  
	        String url = "jdbc:mysql://localhost:3306/";  
	        String dbName = "city";  
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	        	Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(url + dbName, userName, password);  
	            
	        
	  
	            pst = conn  
	                    .prepareStatement("insert into city_login_student (username,password) VALUES (?,?);");  
	            pst.setString(1, user);  
	            pst.setString(2, pass);  
	  
	            rs = pst.executeUpdate();  
	            
	            
	            
	            
	         /*   
	            String sql= "insert into city_login_student (username,password) VALUES * (?,?);";
	            pst= conn.createStatement();
	            rs= pst.executeQuery(sql);*/
	            

	           /* String simpleProc = "{ call Sample_test(?,?,?,?) }";
	            CallableStatement cs = conn.prepareCall("{Sample_test}");*/
	           // cs.setString(1, teacher_id);
	           // cs.setString(2, username);
	          //  cs.setString(3, userPass);
	            //cs.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
	  
	            // rs = cs.executeQuery();
	           
	        } catch (Exception e) 
	        {  
	            System.out.println(e);  
	        } 
	        finally
	        {  
	            if (conn != null) 
	            {  
	                try {  
	                    conn.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	           
	           
	        }  
	       
	       
	 }
}