package com.MovieBackIn.registeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.MovieBackIn.util.Mailer;
import com.mysql.cj.Session;

/**
 * Servlet implementation class RegisterationServlet
 */
@WebServlet("/RegisterationServlet")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname=request.getParameter("name");
		String mail=request.getParameter("email");
		String upass=request.getParameter("pass");
		String mobile =request.getParameter("contact");
		String role = request.getParameter("role");
		String enc=null;
		try {
			enc = Encryptpass.encrypt(upass);
			System.out.print(enc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int x=0;
		boolean y=false;
		RequestDispatcher dispatcher=null;
		Connection con=null;
		try {
			if(enc!=null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/users?useSSL=false","root","navel@4392");
			PreparedStatement pst = con.prepareStatement("select * from users where user_email = ? OR user_name = ? ");
			pst.setString(1,mail);
			pst.setString(2,uname);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				request.setAttribute("status", "Already exists");
				dispatcher=request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			}
			
			else {
			    HttpSession session = request.getSession();
			    session.setAttribute("uname", uname);
			    session.setAttribute("mobile", mobile);
			    session.setAttribute("mail", mail);
			    session.setAttribute("encPass", enc);
			    session.setAttribute("role", role);

			    response.sendRedirect("OtpServlet?email=" + mail);
			}
			}
			
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}	
		}
	}

