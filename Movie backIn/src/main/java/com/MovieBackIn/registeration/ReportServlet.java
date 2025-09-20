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
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String user = request.getParameter("b_name");

        try {
        	Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "navel@4392");
             PreparedStatement pst = con.prepareStatement(
                    "insert into report(m_id,user_name) values(?,?)");

            Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setString(1, id);
            pst.setString(2, user);
            int rows=0;
            rows = pst.executeUpdate();
            PreparedStatement pst1 = con.prepareStatement("UPDATE users SET report_count = report_count+1  WHERE user_name=(SELECT m_seller FROM movies where m_id= ? )");
            pst1.setString(1,id);
          pst1.executeUpdate();
          request.setAttribute("done", rows > 0 ? "success" : "failed");
            request.getRequestDispatcher("buy.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("done", "error");
            request.getRequestDispatcher("buy.jsp").forward(request, response);
        }
    }
}

