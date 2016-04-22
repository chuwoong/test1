package com.stitch.tumblr.member.bean;

import java.util.Date;

public class MemberVO {
	private String m_id;
	private String m_password;
	private String m_passwordCheck;
	private String m_name;
	private Date birth;
	private String m_gender;
	
	public String getM_id() {
		return m_id;
	}
	
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getM_password() {
		return m_password;
	}
	
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	
	public String getM_passwordCheck() {
		return m_passwordCheck;
	}
	
	public void setM_passwordCheck(String m_passwordCheck) {
		this.m_passwordCheck = m_passwordCheck;
	}
	
	public String getM_name() {
		return m_name;
	}
	
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
}
