package com.luv2code.testjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestJdbcConnection")
public class TestJdbcConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user="root";
		String password=""; 
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";

		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to databse: "+jdbcUrl);
			
			Class.forName(driver);
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,password);
			out.println("SUCCESS!!");
			myConn.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}		

	}

}
