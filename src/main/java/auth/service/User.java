package auth.service;

public class User {

	private String id;
	private String name;
	private int mNumber;

	public User(String id, String name, int mNumber) {
		this.id = id;
		this.name = name;
		this.mNumber = mNumber;
	}

	public int getmNumber() {
		return mNumber;
	}
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
