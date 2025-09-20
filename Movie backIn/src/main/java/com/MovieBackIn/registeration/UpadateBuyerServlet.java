package com.MovieBackIn.registeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpadateBuyerServlet
 */
@WebServlet("/UpadateBuyerServlet")
public class UpadateBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String b_name = request.getParameter("b_name");
        System.out.println("UpdateBuyerServlet: id=" + id + " b_name=" + b_name);

        try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "navel@4392");
             PreparedStatement pst = con.prepareStatement(
                    "UPDATE movies SET m_buyer = ? WHERE m_id = ?")) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setString(1, b_name);
            pst.setString(2, id);

            int rows = pst.executeUpdate();
            request.setAttribute("done", rows > 0 ? "success" : "failed");
            request.getRequestDispatcher("buy.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("done", "error");
            request.getRequestDispatcher("buy.jsp").forward(request, response);
        }
    }
}

