package com.MovieBackIn.registeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

/**
 * Servlet implementation class SellerServlet
 */
@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m_name=request.getParameter("m_name");
		String location=request.getParameter("location");
		String theatre=request.getParameter("theatre");
		String date =request.getParameter("date");
		String screen = request.getParameter("screen");
		String seat_no=request.getParameter("seat_no");
		String genre=request.getParameter("genre");
		String S_name=request.getParameter("s_name");
		String b_name=null;
		float price=Float.parseFloat(request.getParameter("price"));
		RequestDispatcher dispatcher=null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true","root","navel@4392");
			PreparedStatement pst = con.prepareStatement("select * from movies where m_name = ? and m_location = ? and m_theatre = ? and (? >= DATE_SUB(m_date, INTERVAL 30 MINUTE) AND ? <= DATE_ADD(m_date, INTERVAL 30 MINUTE)) and m_screen = ? and  m_seatno = ?");
			pst.setString(1,m_name);
			pst.setString(2,location);
			pst.setString(3,theatre);
			pst.setString(4,date);
			pst.setString(5,date);
			pst.setString(6,screen);
			pst.setString(7,seat_no);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				request.setAttribute("status", "ticket already exists");
				System.out.print("im in");
				dispatcher=request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
				else {
				pst = con.prepareStatement("insert into users.movies(m_name,m_seatno,m_screen,m_theatre,m_location,m_price,m_genre,m_seller,m_buyer,m_date) values(?,?,?,?,?,?,?,?,?,?)");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	            LocalDateTime DateTime = LocalDateTime.parse(date, formatter);
	            Timestamp timestamp = Timestamp.valueOf(DateTime);
				pst.setString(1,m_name);
				pst.setString(2,seat_no);
				pst.setString(3,screen);
				pst.setString(4,theatre);
				pst.setString(5,location);
				pst.setFloat(6,price);
				pst.setString(7,genre);
				pst.setString(8,S_name);
				pst.setString(9,b_name);
				pst.setTimestamp(10,timestamp);	
				int row=pst.executeUpdate();
				dispatcher=request.getRequestDispatcher("index.jsp");
				if (row>0) {
					request.setAttribute("status", "Success");
					PreparedStatement pst1 = con.prepareStatement("UPDATE users SET user_role = ? WHERE user_name = ?");
					pst1.setString(1, "seller");
		            pst1.setString(2, S_name);
		            pst1.executeUpdate();
					
				}
				else {
					request.setAttribute("status", "failed");
				}
				dispatcher.forward(request, response);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
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
	

