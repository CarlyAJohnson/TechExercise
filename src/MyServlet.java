import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//The following websites/tutorials were reference to aid in the completion of this project. Some of this code is from those web sites and not original.
//http://www.javaknowledge.info/search-from-database-using-servlet-and-jsp/
//http://www.javawebtutor.com/articles/servlets/servlet_db_example.php
//http://www.codejava.net/coding/jsp-servlet-jdbc-mysql-create-read-update-delete-crud-example
//http://www.c-sharpcorner.com/UploadFile/fd0172/how-to-fetch-records-from-database-using-servlet-in-java/
//https://stackoverflow.com/questions/26702873/search-from-database-java-servlet
//http://www.theserverside.com/discussions/thread/4369.html

@WebServlet("/Myservlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyServlet() {
		super();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getServletPath();
		
		try{
			switch (action) {
			case "/insert":
				insert(request,response);
				break;
			case "/search":
				search(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String user = request.getParameter("MYUSER");
		String email = request.getParameter("EMAIL");
		String phone = request.getParameter("PHONE");
		String firstName = request.getParameter("FIRSTNAME");
		String lastName = request.getParameter("LASTNAME");
		String address = request.getParameter("ADDRESS");
		String age = request.getParameter("AGE");
		String insurance = request.getParameter("INSURANCE");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://johnsontechexercise.ddns.net:3306/myDB", "newremoteuser", "password");
			PreparedStatement ps = connection.prepareStatement("insert into myTable values(?,?,?,?,?,?,?,?)");
			
			ps.setString(1, user);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, address);
			ps.setString(7, age);
			ps.setString(8, insurance);
			
			int i = ps.executeUpdate();
			out.println("<html><body style='background-color: lightblue;'><h1 style='text-align:center;'>Welcome to Dr.Johnson's Patient Portal!</h1><h2 style='text-align: center;'>"
					+ "The Below Information was Entered into the Patient Database as a New Record</h2>");
			out.println("<table width='700px' align='center' style='background-color: white; border:1px solid #000000;'><tr>"
					+ "<td colspan=8 align='center' style='background-color:#2F4F4F; color: white;'><b>New Patient Record</b></td></tr>"
					+ "<tr align='center' style='background-color: lightgrey;'><td> Patient ID </td><td>Email</td><td>Phone</td><td>First Name</td>"
					+ "<td>Last Name</td><td>Address</td><td>Age</td><td>Insurance</td></tr>");
			out.println("<tr><td>" + user + "</td><td>" + email + "</td><td>" + phone + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + address + "</td>"
					+ "<td>" + age + "</td><td>" + insurance + "</td></tr></table>"
					+ "</body></html>");
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
	
	private void search (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String search = request.getParameter("search");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://johnsontechexercise.ddns.net:3306/myDB", "newremoteuser", "password");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM myTable WHERE MYUSER LIKE ?");
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			out.println("<html><body style='background-color: lightblue;'><h1 style='text-align:center;'>Welcome to Dr.Johnson's Patient Portal!</h1><h2 style='text-align: center;'>Below is Your Patient Record:</h2>");
			out.println("<table width='700px' align='center' style='background-color: white; border:1px solid #000000;'><tr>"
					+ "<td colspan=8 align='center' style='background-color:#2F4F4F; color: white;'><b>Patient Record</b></td></tr>"
					+ "<tr align='center' style='background-color: lightgrey;'><td> Patient ID </td><td>Email</td><td>Phone</td><td>First Name</td>"
					+ "<td>Last Name</td><td>Address</td><td>Age</td><td>Insurance</td></tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString("MYUSER") + "</td>");
				out.println("<td>" + rs.getString("EMAIL") + "</td>");
				out.println("<td>" + rs.getString("PHONE") + "</td>");
				out.println("<td>" + rs.getString("FIRSTNAME") + "</td>");
				out.println("<td>" + rs.getString("LASTNAME") + "</td>");
				out.println("<td>" + rs.getString("ADDRESS") + "</td>");
				out.println("<td>" + rs.getString("AGE") + "</td>");
				out.println("<td>" + rs.getString("INSURANCE") + "</td>");
				out.println("</tr>");
			}
			out.println("</table></body></html>");			
		} catch (Exception e3){
			System.out.println(e3);
		}
	}
}
