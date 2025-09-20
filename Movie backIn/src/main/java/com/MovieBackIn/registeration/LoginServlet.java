package com.MovieBackIn.registeration;
import com.MovieBackIn.util.Mailer;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail=request.getParameter("email");
		String upass=request.getParameter("password");
		String enc=null;
		try {
			enc = Encryptpass.encrypt(upass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection con=null;
		try {
			if (enc!=null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Users?useSSL=false&allowPublicKeyRetrieval=true", "root", "navel@4392");
			PreparedStatement pst1 = con.prepareStatement("UPDATE movies SET m_buyer = ? WHERE m_date < NOW() AND m_buyer IS null");
			pst1.setString(1,"Expired");
			pst1.executeUpdate();
			PreparedStatement pst = con.prepareStatement("select * from users where user_email = ? and user_pass =? and ban_flag = 0");
			pst.setString(1,mail);
			pst.setString(2,enc);
			
			ResultSet rs=pst.executeQuery();
			HttpSession session=request.getSession();
			RequestDispatcher dispatcher=null;
			if (rs.next()) {
				bubobj newUser = new bubobj(rs.getInt("id"),rs.getString("user_name"),rs.getString("user_email"), rs.getString("user_mobile"), rs.getString("user_role"), rs.getString("ban_flag"));
				session.setAttribute("name",newUser );
				dispatcher =request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher =request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
		}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally 
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}
