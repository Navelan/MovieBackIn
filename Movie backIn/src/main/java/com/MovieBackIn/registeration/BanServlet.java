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
 * Servlet implementation class BanServlet
 */
@WebServlet("/BanServlet")
public class BanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "navel@4392");
             PreparedStatement pst = con.prepareStatement("UPDATE users SET ban_flag = NOT ban_flag WHERE id = ?")) {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            System.out.print(rows);
            request.setAttribute("done", rows > 0 ? "success" : "failed");
            request.getRequestDispatcher("AdminManage.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("done", "error");
            request.getRequestDispatcher("AdminManage.jsp").forward(request, response);
        }
    }
}

