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

@WebServlet("/ManageSellerServlet")
public class ManageSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String m_name = request.getParameter("m_name");
        String location = request.getParameter("location");
        String theatre = request.getParameter("theatre");
        String date = request.getParameter("date");
        String genre = request.getParameter("genre");
        String b_name = request.getParameter("b_name");
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
                "AND (? = m_buyer)"
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
            pst.setString(12, b_name);
            rs = pst.executeQuery();
            pst1 = con.prepareStatement(
                "SELECT * FROM movies " +
                "WHERE (? = '' OR m_name LIKE ?) " +
                "AND (? = '' OR m_location LIKE ?) " +
                "AND (? = '' OR m_theatre LIKE ?) " +
                "AND (? = '' OR m_genre LIKE ?) " +
                "AND (? = '' OR m_date BETWEEN DATE_SUB(?, INTERVAL 30 MINUTE) AND DATE_ADD(?, INTERVAL 30 MINUTE)) " +
                "AND (? = m_seller)"
            );
            pst1.setString(1, m_name);
            pst1.setString(2, "%" + m_name + "%");
            pst1.setString(3, location);
            pst1.setString(4, "%" + location + "%");
            pst1.setString(5, theatre);
            pst1.setString(6, "%" + theatre + "%");
            pst1.setString(7, genre);
            pst1.setString(8, "%" + genre + "%");
            pst1.setString(9, date);
            pst1.setString(10, date);
            pst1.setString(11, date);
            pst1.setString(12, b_name);
            rs1 = pst1.executeQuery();

            // Buyer list
            ArrayList<movie> movies = new ArrayList<>();
            while (rs.next()) {
                movie m1 = new movie(
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
                movies.add(m1);
            }
            System.out.print("0"+movies.size());
            request.setAttribute("movies", movies);
            ArrayList<movie> movies1 = new ArrayList<>();
            while (rs1.next()) {
                movie m2 = new movie(
                    rs1.getInt("m_id"),
                    rs1.getString("m_name"),
                    rs1.getString("m_location"),
                    rs1.getTimestamp("m_date"),
                    rs1.getString("m_theatre"),
                    rs1.getString("m_screen"),
                    rs1.getString("m_buyer"),
                    rs1.getString("m_genre"),
                    rs1.getString("m_seatno"),
                    rs1.getFloat("m_price")
                );
                movies1.add(m2);
            
            }
            System.out.print("1"+movies1.size());
            request.setAttribute("movies1", movies1);
            RequestDispatcher rd = request.getRequestDispatcher("Manage.jsp");
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
