package member.form;

import java.sql.Date;
import java.util.Map;

public class JoinRequest {

	private String id;
	private String pw;
	private String confirmPw;
	private String name;
	private Date birth;
	private String gender;
	private String email;
	private String phone;

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

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public boolean isPwEqualToConfirm() {
		return pw != null && pw.equals(confirmPw);
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, pw, "pw");
		checkEmpty(errors, confirmPw, "confirmPw");
		checkEmpty(errors, name, "name");
		//checkEmpty(errors, birth, "birth");
		checkEmpty(errors, gender, "gender");
		checkEmpty(errors, email, "email");
		checkEmpty(errors, phone, "phone");
		if (!errors.containsKey("confirmPw")) {
			if (!isPwEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
