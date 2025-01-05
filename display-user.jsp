<%@ page import="com.hms.classes" %>
<%
    HttpSession session = request.getSession(false);
    if (session != null) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            out.println("User ID: " + user.getUserId());
            out.println("Full Name: " + user.getFullName());
            out.println("Email: " + user.getEmail());
            out.println("Phone Number: " + user.getPhoneNumber());
        } else {
            out.println("No user information found in session.");
        }
    } else {
        out.println("No session found.");
    }
%>