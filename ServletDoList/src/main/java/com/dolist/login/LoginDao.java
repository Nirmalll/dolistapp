package com.dolist.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	public boolean check(String email, String password) {

		try {
			String sql = "select * from register where email=? and password=?";
			String url = "jdbc:mysql://localhost:3306/dolist";
			String username = "root";
			String pass = "06061992";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, pass);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
