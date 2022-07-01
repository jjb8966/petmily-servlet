package member.model;

import java.sql.Date;

public class Member {

	private int mNumber;
	private String id;
	private String pw;
	private String name;
	private Date birth;
	private String gender;
	private String email;
	private String phone;
	private String grade;
	public Member(String pw, String name, String email, String phone,String id) {
		
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.id = id;
	}

	
	public Member(int mNumber, String id, String pw, String name, Date birth, String gender, String email, String phone, String grade) {
		this.mNumber = mNumber;
		this.gender = gender;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.grade = grade;
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Member [mNumber=" + mNumber + ", id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", grade=" + grade + "]";
	}


	// insert瑜� �쐞�븳 �깮�꽦�옄
	public Member(String id, String pw, String name, Date birth, String gender, String email, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public boolean matchPw(String pwd) {
		return pw.equals(pwd);
	}

	public void changePw(String newPwd) {
		this.pw = newPwd;
	}
}
