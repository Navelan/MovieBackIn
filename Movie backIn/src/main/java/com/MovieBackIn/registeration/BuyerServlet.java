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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Servlet implementation class BuyerServlet
 */
@WebServlet("/BuyerServlet")
public class BuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String m_name=request.getParameter("m_name");
		String location=request.getParameter("location");
		String theatre=request.getParameter("theatre");
		String date =request.getParameter("date");
		String genre=request.getParameter("genre");
		String b_name=request.getParameter("b_name");
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true","root","navel@4392");
			
			PreparedStatement pst = con.prepareStatement(
				    "SELECT * FROM movies " +
				    "WHERE (? = '' OR m_name LIKE ?) " +
				    "AND (? = '' OR m_location LIKE ?) " +
				    "AND (? = '' OR m_theatre LIKE ?) " +
				    "AND (? = '' OR m_genre LIKE ?) " +
				    "AND (? = '' OR m_date BETWEEN DATE_SUB(?, INTERVAL 30 MINUTE) AND DATE_ADD(?, INTERVAL 30 MINUTE)) " +
				    "AND (m_buyer IS NULL) " +
				    "AND (m_seller != ?)"+
				    "AND m_id NOT IN (SELECT m_id FROM report WHERE user_name=?)"
				);

				pst.setString(1, m_name);
				pst.setString(2, "%" + m_name + "%");

				pst.setString(3, location);
				pst.setString(4, "%" + location + "%");

				pst.setString(5, theatre);
				pst.setString(6, "%" + theatre + "%");

				pst.setString(7, genre);
				pst.setString(8, "%" + genre + "%");
				if (date == null || date.isEmpty()) {
				    pst.setString(9, "");
				    pst.setTimestamp(10, null);
				    pst.setTimestamp(11, null);
				} else {
				    Timestamp ts = Timestamp.valueOf(date + " 00:00:00");
				    pst.setString(9, date);
				    pst.setTimestamp(10, ts);
				    pst.setTimestamp(11, ts);
				}

				pst.setString(12, b_name);
				pst.setString(13, b_name);

			ResultSet rs=pst.executeQuery();
			ArrayList<movie> movies = new ArrayList<>();
            while (rs.next()) {
            	movie m1=new movie(rs.getInt("m_id"),rs.getString("m_name"),rs.getString("m_location"), rs.getTimestamp("m_date"),rs.getString("m_theatre"), rs.getString("m_screen"), rs.getString("m_seller"),rs.getString("m_genre"), rs.getString("m_seatno"),rs.getFloat("m_price"));
                movies.add(m1);
            }

            request.setAttribute("movies", movies);
            RequestDispatcher rd = request.getRequestDispatcher("buy.jsp");
            rd.forward(request, response);
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
	

