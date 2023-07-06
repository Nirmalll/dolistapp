package com.dolist.registers.servlet;

public class Member {

	String uname;
	String password;
	String email;
	String number;

	public Member(String uname, String password, String email, String number) {
		super();
		this.uname = uname;
		this.password = password;
		this.email = email;
		this.number = number;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
