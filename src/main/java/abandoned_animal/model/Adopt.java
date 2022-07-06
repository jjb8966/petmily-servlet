package abandoned_animal.model;

public class Adopt {

	private int adNumber; // 입양 번호
	private int mNumber;
	private int abNumber;
	private String residence;
	private String maritalStatus;
	private String job;
	private String status = "처리중";

	public Adopt(int mNumber, int abNumber, String residence, String maritalStatus, String job) {
		this.mNumber = mNumber;
		this.abNumber = abNumber;
		this.residence = residence;
		this.maritalStatus = maritalStatus;
		this.job = job;
	}

	public int getAdNumber() {
		return adNumber;
	}

	public void setAdNumber(int adNumber) {
		this.adNumber = adNumber;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public int getAbNumber() {
		return abNumber;
	}

	public void setAbNumber(int abNumber) {
		this.abNumber = abNumber;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
