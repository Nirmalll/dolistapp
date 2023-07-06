package com.dolist.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		LoginDao user = new LoginDao();

		if (user.check(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("allactivity.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}

	}

}
