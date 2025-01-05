package com.hms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hms.classes.*;
import com.hms.dao.*;
import com.hms.driver.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HotelService hotelService = new HotelService();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().append("Served at: ").append(request.getContextPath());
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/HMS/src/main/webapp/jsp/login.jsp");
    	dispatcher.forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        response.sendRedirect("../homepage-admin.html");

        if (hotelService.userVerification(userId, password)) {
            if (hotelService.isAdmin(userId)) {
                response.sendRedirect("homepage-admin.html");
            } else {
                response.sendRedirect("#");
            }
        } else {
            response.sendRedirect("login.html?error=Invalid credentials");
        }
    }
}