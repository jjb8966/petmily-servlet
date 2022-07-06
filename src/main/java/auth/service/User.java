package auth.service;

public class User {

	private int mNumber;
	private String id;
	private String name;

	public User(int mNumber, String id, String name) {
		this.mNumber = mNumber;
		this.id = id;
		this.name = name;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
