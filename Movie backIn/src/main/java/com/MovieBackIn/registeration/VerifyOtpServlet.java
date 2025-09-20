package com.MovieBackIn.registeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/VerifyOtpServlet")
public class VerifyOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userOtp = request.getParameter("otp");
        String generatedOtp = (String) session.getAttribute("otp");

        RequestDispatcher dispatcher;

        if (generatedOtp != null && userOtp.equals(generatedOtp)) {
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/users?useSSL=false", "root", "navel@4392")) {

                String uname = (String) session.getAttribute("uname");
                String mail = (String) session.getAttribute("mail");
                String mobile = (String) session.getAttribute("mobile");
                String encPass = (String) session.getAttribute("encPass");
                String role = (String) session.getAttribute("role");

                PreparedStatement pst = con.prepareStatement(
                        "insert into Users.users(user_name,user_mobile,user_email,user_pass,user_role,report_count,ban_flag) values(?,?,?,?,?,?,?)");
                pst.setString(1, uname);
                pst.setString(2, mobile);
                pst.setString(3, mail);
                pst.setString(4, encPass);
                pst.setString(5, role);
                pst.setInt(6, 0);
                pst.setBoolean(7, false);

                int row = pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("registration.jsp");
                if (row > 0) {
                    request.setAttribute("status", "Success");
                } else {
                    request.setAttribute("status", "failed");
                }
                dispatcher.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("status", "noOtp");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
