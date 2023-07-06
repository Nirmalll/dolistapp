package com.dolist.registers.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDao {

	public String insert(Member member) {

		try {
			String sql = "insert into register values (?,?,?,?)";
			String url = "jdbc:mysql://localhost:3306/dolist";
			String username = "root";
			String password = "06061992";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getUname());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getNumber());
			ps.executeUpdate();

		}

		catch (Exception e) {
			System.out.println(e);

		}
		return null;

	}
}
