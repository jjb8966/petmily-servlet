package member.model;

import java.sql.Date;

public class Member {

	private String id;
	private String pw;
	private String name;
	private Date birth;
	private String gender;
	private String email;
	private String phone;
	private int mNumber;

	public Member(String id, String pw, String name, Date birth, String gender, String email, String phone, int mNumber) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.mNumber = mNumber;
	}

	public String getId() {
		return id;
	}
	
	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public Date getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	public boolean matchPw(String pwd) {
		return pw.equals(pwd);
	}

	public void changePw(String newPwd) {
		this.pw = newPwd;
	}

	public int getmNumber() {
		return mNumber;
	}

	
	

}
