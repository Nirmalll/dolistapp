package com.dolist.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoListDao {

	String url = "jdbc:mysql://localhost:3306/dolist";
	String username = "root";
	String password = "06061992";

	private static final String INSERT_ACTIVITY_SQL = "insert into dlist" + "(activity,description,date,status) values"
			+ "(?,?,?,?)";
	private static final String SELECT_DOLIST_BY_ID = "select activity,description,date,status from dlist where id=?";
	private static final String SELECT_ALL_ACTIVITY = "select * from dlist";
	private static final String DELETE_ACTIVITY_SQL = "delete from dlist where id=?";
	private static final String UPDATE_ACTIVITY_SQL = "update dlist set activity=?,description=?,date=?,status=? where id=?";

	public Connection getConnection() {

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;

	}

	public void insert(DoListMember member) throws SQLException {           //insert activity
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_ACTIVITY_SQL);

			ps.setString(1, member.getActivity());
			ps.setString(2, member.getDescription());
			ps.setString(3, member.getDate());
			ps.setString(4, member.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	
	
	public DoListMember selectactivity(int id) {          //select activity by id
		DoListMember member = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_DOLIST_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String activity = rs.getString("activity");
				String description = rs.getString("description");
				String date = rs.getString("date");
				String status = rs.getString("status");
				member = new DoListMember(id, activity, description, date, status);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return member;
		
	}
	
	public List<DoListMember> selectallactivity() {          //select all activity
		List<DoListMember> member = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_ACTIVITY);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String activity = rs.getString("activity");
				String description = rs.getString("description");
				String date = rs.getString("date");
				String status = rs.getString("status");
				member.add(new DoListMember(id, activity, description, date, status)) ;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return member;
		
	}
	
	public boolean updateactivity(DoListMember member) throws SQLException {      // update
		boolean rowupdated = false;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_ACTIVITY_SQL);
			ps.setString(1, member.getActivity());
			ps.setString(2, member.getDescription());
			ps.setString(3, member.getDate());
			ps.setString(4, member.getStatus());
			ps.setInt(5, member.getId());
			rowupdated	=ps.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();

		}

		return rowupdated;

	}
	
	public boolean deleteactivity(int id) throws SQLException { // delete
		boolean rowupdated = false;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_ACTIVITY_SQL);
			ps.setInt(1, id);
			rowupdated = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return rowupdated;

	}
	
	

}
