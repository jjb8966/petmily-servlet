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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMNumber() {
		return mNumber;
	}
}
