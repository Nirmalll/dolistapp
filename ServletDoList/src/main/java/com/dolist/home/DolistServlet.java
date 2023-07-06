package com.dolist.home;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DolistServlet
 */
@WebServlet("/")
public class DolistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public DoListDao dolistdao;
    
    public DolistServlet() {
        this.dolistdao= new DoListDao();
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	this.doGet(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String action =request.getServletPath();
		
		switch(action) {
		case "/new":
			shownewform(request,response);
			break;
		case "/insert":
			try {
				insertactivity(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteactivity(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				update(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			break;
		case "/edit":
			try {
				showeditform(request, response);
			} catch (IOException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (ServletException e) {

				e.printStackTrace();
			}
			break;
			default :
			try {
				listactivity(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
				break;
		}
	}
	
	
	public void insertactivity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String activity = request.getParameter("activity");
		String description = request.getParameter("description");
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		DoListMember member = new DoListMember(activity,description,date,status);
		dolistdao.insert(member);
		response.sendRedirect("list");
		
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String activity = request.getParameter("activity");
		String description = request.getParameter("description");
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		DoListMember member = new DoListMember(id,activity,description,date,status);
		dolistdao.updateactivity(member);
		response.sendRedirect("list");
		}
	
	
	public void shownewform(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("activityform.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void deleteactivity(HttpServletRequest request, HttpServletResponse response) throws  IOException,SQLException  {

		int id =Integer.parseInt(request.getParameter("id"));
		dolistdao.deleteactivity(id);
		response.sendRedirect("list");
		}
	
	public void listactivity(HttpServletRequest request, HttpServletResponse response) throws  IOException,SQLException, ServletException  {
	
	List<DoListMember> allactivity	=dolistdao.selectallactivity();
	request.setAttribute("activity", allactivity);
	RequestDispatcher dispatcher = request.getRequestDispatcher("allactivity.jsp");
	dispatcher.forward(request, response);
	
	}
	public void showeditform(HttpServletRequest request, HttpServletResponse response) throws  IOException,SQLException, ServletException  {
     int id = Integer.parseInt(request.getParameter("id"));
    DoListMember exitingactivity =dolistdao.selectactivity(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("activityform.jsp");
    request.setAttribute("activity", exitingactivity);
	dispatcher.forward(request, response);
    
	}
}
