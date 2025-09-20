package com.MovieBackIn.registeration;

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
import java.sql.SQLException;

import com.MovieBackIn.util.Mailer;

/**
 * Servlet implementation class OtpServlet
 */
@WebServlet("/OtpServlet")
public class OtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        HttpSession session = request.getSession();
	        try {
	            String otp = Mailer.sendOtp(email);
	            session.setAttribute("otp", otp);
	            response.sendRedirect("VerifyOTP.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("registration.jsp?status=failed");
	        }
	    }
	}