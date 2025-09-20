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
import java.util.ArrayList;

/**
 * Servlet implementation class AdminManageServlet
 */
@WebServlet("/AdminManageServlet")
public class AdminManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m_name = request.getParameter("m_name");
        String location = request.getParameter("location");
        String theatre = request.getParameter("theatre");
        String date = request.getParameter("date");
        String genre = request.getParameter("genre");
        String b_name = request.getParameter("b_name");
        String s_name = request.getParameter("s_name");
        String u_name = request.getParameter("u_name");
        String mail = request.getParameter("mail");
        String mobile = request.getParameter("mobile");
        String role = request.getParameter("role");
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "navel@4392");
            pst = con.prepareStatement(
                "SELECT * FROM movies " +
                "WHERE (? = '' OR m_name LIKE ?) " +
                "AND (? = '' OR m_location LIKE ?) " +
                "AND (? = '' OR m_theatre LIKE ?) " +
                "AND (? = '' OR m_genre LIKE ?) " +
                "AND (? = '' OR m_date BETWEEN DATE_SUB(?, INTERVAL 30 MINUTE) AND DATE_ADD(?, INTERVAL 30 MINUTE)) " +
                "AND (? = '' OR m_seller LIKE ?) " +
                "AND (? = '' OR m_buyer LIKE ?) " 
            );
            pst.setString(1, m_name);
            pst.setString(2, "%" + m_name + "%");
            pst.setString(3, location);
            pst.setString(4, "%" + location + "%");
            pst.setString(5, theatre);
            pst.setString(6, "%" + theatre + "%");
            pst.setString(7, genre);
            pst.setString(8, "%" + genre + "%");
            pst.setString(9, date);
            pst.setString(10, date);
            pst.setString(11, date);
            pst.setString(12, s_name);
            pst.setString(13, "%"+s_name+"%");
            pst.setString(14, b_name);
            pst.setString(15, "%"+b_name+"%");
            rs = pst.executeQuery();
            
            ArrayList<movie> movies = new ArrayList<>();
            while (rs.next()) {
                movie m2 = new movie(
                    rs.getInt("m_id"),
                    rs.getString("m_name"),
                    rs.getString("m_location"),
                    rs.getTimestamp("m_date"),
                    rs.getString("m_theatre"),
                    rs.getString("m_screen"),
                    rs.getString("m_seller"),
                    rs.getString("m_genre"),
                    rs.getString("m_seatno"),
                    rs.getFloat("m_price")
                );
                m2.setBuyer(rs.getString("m_buyer"));
                movies.add(m2);
            }
            pst1 = con.prepareStatement(
                "SELECT * FROM users " +
                "WHERE (? = '' OR user_name LIKE ?) " +
                "AND (? = '' OR user_email LIKE ?) " +
                "AND (? = '' OR user_mobile LIKE ?) " +
                "AND (? = '' OR user_role LIKE ?) "
            );
            pst1.setString(1, u_name);
            pst1.setString(2, "%" + u_name + "%");
            pst1.setString(3, mail);
            pst1.setString(4, "%" + mail + "%");
            pst1.setString(5, mobile);
            pst1.setString(6, "%" + mobile + "%");
            pst1.setString(7, role);
            pst1.setString(8, "%" + role + "%");
            rs1 = pst1.executeQuery();
            System.out.println("Movies found: " + movies.size());
            request.setAttribute("movies", movies);
            // Users list
            ArrayList<bubobj> users = new ArrayList<>();
            while (rs1.next()) {
                bubobj m1 = new bubobj(
                    rs1.getInt("id"),
                    rs1.getString("user_name"),
                    rs1.getString("user_email"),
                    rs1.getString("user_mobile"),
                    rs1.getString("user_role"),
                    rs1.getString("ban_flag")
                );
                users.add(m1);
            }
            request.setAttribute("users", users);
            
            
            RequestDispatcher rd = request.getRequestDispatcher("AdminManage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (rs1 != null) rs1.close(); } catch (SQLException ignored) {}
            try { if (pst != null) pst.close(); } catch (SQLException ignored) {}
            try { if (pst1 != null) pst1.close(); } catch (SQLException ignored) {}
            try { if (con != null) con.close(); } catch (SQLException ignored) {}
        }
    }
}
