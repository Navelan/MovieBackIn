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
 * Servlet implementation class RemoveTicketServlet
 */
@WebServlet("/RemoveTicketServlet")
public class RemoveTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String referer = request.getParameter("head");
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String para="";
        if(type.equals("movies")) {
        	para="m_";
        	}
        
        try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "navel@4392");
             PreparedStatement pst = con.prepareStatement("DELETE FROM "+type+" WHERE "+para+"id = ?")) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setString(1, id);
            int rows = pst.executeUpdate();
            System.out.print(rows);
            request.setAttribute("done", rows > 0 ? "success" : "failed");
            request.getRequestDispatcher("/"+referer).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("done", "error");
            request.getRequestDispatcher("/"+referer).forward(request, response);
        }
    }
}

