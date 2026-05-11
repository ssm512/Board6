package com.green.users.dto;

public class UserDTO {
	// field 
    private	String userid;
    private	String password;
    private	String username;
    private	String email;
    private int upoint;
    private	String regdate;
	// Constructor
    public UserDTO () {}
    public UserDTO(String userid, String password, String username, String email, int upoint, String regdate) {
    	this.userid = userid;
    	this.password = password;
    	this.username = username;
    	this.email = email;
    	this.upoint = upoint;
    	this.regdate = regdate;
    }
	// Getter&Setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	// toString
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", password=" + password + ", username=" + username + ", email=" + email
				+ ", upoint=" + upoint + ", regdate=" + regdate + "]";
	}
}
