
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;


class Candidate extends Thread {   
    public static String _host = "47.100.38.198:5433";
    public static String _dbname = "dba0e1c7b6890b4b6797af5fc2763b8d7bcom_text";
    public static String _dbuser = "KevinKEE";
    public static String _dbpassword = "aA123456";
}  



		

public class InsertSqldb {
	public static void main(String[] args) {
		 try {

				Reader reader= new InputStreamReader(new FileInputStream("C:\\COMDATA\\Rec00.txt"),"UTF-8"); 
				
				BufferedReader buf = new BufferedReader(reader); 
				String bufToString = "";
				String readLine = "";
				String[] myArray = new String[100];  
				
			 
			 
			 
		      // Create the DB connection
		      Class.forName("org.postgresql.Driver");
		      Connection connection = null;
		      connection = DriverManager.getConnection(
		                 "jdbc:postgresql://"+ Candidate._host + "/" + Candidate._dbname ,Candidate._dbuser, Candidate._dbpassword);
		      	
		      String insertStmt = null;
		      PreparedStatement preStmt;

		      
		       
			    
		      //获取行
		      int i = 0;
	            while((readLine = buf.readLine()) != null){			
	                myArray[i] = readLine;
	                i++;
	            }
	            
	            
	            String time =  myArray[0];//时间
                String value = myArray[1];//第二行的所有内容
                
                System.out.println(time);

                
                String[] valueArr = value.split(" "); //以空格隔开
                int len = valueArr.length;
                

                //循环整个数组
                for (int a = 0 ; a < len; a++) {
                	
                	System.out.println(valueArr[a]);
                	
                	String[] valueArray = valueArr[a].split(":");
                	int lenr = valueArray.length;
                    
//                    System.out.println(lenr); //2

                    
                		preStmt = connection.prepareStatement(insertStmt = "INSERT INTO txt_db_java (btime, name,num,alevel)  VALUES (?,?,?,?);");
                		
                        preStmt.setString( 1, time);

        	            preStmt.setString(2, valueArray[0]);
        	            
        	            preStmt.setString(3, valueArray[1]);
        	            
        	            
        	            //判断气体是否异常
        	            
        	            String vaString = valueArray[1];
        	            
        	            int l =Integer.parseInt(vaString);
        	            
        	            if (l > 80 && l < 150) {
        	            	preStmt.setString(4, "!气体异常!");
        	            	  System.out.println("气体异常"); 
						}else if(l > 150) {
							preStmt.setString(4, "!!气体危险!!");
							System.out.println("气体危险"); 
						}else {
							preStmt.setString(4, "气体安全");
							System.out.println("气体安全"); 
						}
        	            
        	            

                        preStmt.executeUpdate();
                        preStmt.close();
					
                	

				}
                connection.createStatement().executeUpdate(insertStmt);

			    connection.close();

                
				
            
            		

            	
            	
                
                

			  

	            
		      
	            
		      
		      
		      
		      
			    
			    
		      


		      
		      
		    } catch (Exception e) {
		        System.err.println("Error: " + e.getMessage());
		    }
	}

}
