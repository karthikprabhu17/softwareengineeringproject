package controller;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;






/**
 * Servlet implementation class CreateCommunity
 */

public class CreateCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateCommunity() {
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
		// TODO Auto-generated method stu
		 try {
			 String readLocationDir = request.getParameter("filename");
			 System.out.println(readLocationDir);
	             InputStream input = new BufferedInputStream(new FileInputStream("C:\\Users\\karthik\\Desktop\\hi.xls"));
	           //new FileInputStream("C:\\Users\\Vamsi\\Desktop\\hi.xls"));
	            POIFSFileSystem fs = new POIFSFileSystem( input );
	            HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            String username=null;
                String userPass=null;
	             
	            Iterator rows = sheet.rowIterator();
	            while( rows.hasNext() ) {  
	                HSSFRow row = (HSSFRow) rows.next();
	                System.out.println("\n");
	                Iterator cells = row.cellIterator();
	                while( cells.hasNext() ) {
	                     
	                    HSSFCell cell = (HSSFCell) cells.next();
	                    if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
	                    {
	                    	int col= cell.getColumnIndex();
	                    System.out.print(col+"\n");
	                    System.out.print( cell.getNumericCellValue()+"     " );
	                    }
	                    else
	                    if(HSSFCell.CELL_TYPE_STRING==cell.getCellType())
	                  {
	                   
						if(cell.getColumnIndex()==0)
	                    
	                     username=cell.getStringCellValue();
	                    if(cell.getColumnIndex()==1) 
	                      userPass=cell.getStringCellValue();
	                    
	                   
	                    
	                }
	                       else
	                        if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
	                        System.out.print( cell.getBooleanCellValue()+"    " );
	                        else
	                            if(HSSFCell.CELL_TYPE_BLANK==cell.getCellType())
	                                System.out.print( "BLANK     " );
	                                else
	                            System.out.print("Unknown cell type");
	                   
	                }
	                 
	                 
	            }
	            ReadExcelDal obj1=new ReadExcelDal();
	            obj1.validate(username,userPass);
	            RequestDispatcher requestDispatcher = getServletContext()
						.getRequestDispatcher("/Upload.jsp");
	        	requestDispatcher.include(request, response);
	            
	        } catch ( IOException ex ) {
	            ex.printStackTrace();
	        }
	    }
}
		
