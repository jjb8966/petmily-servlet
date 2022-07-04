package abandoned_animal.model;

import java.sql.Date;

public class TempPet {
	private int tNumber; // 임보 번호
	private int abNumber;
    private int mNumber;
    private Date tempDate;
    private int tempPeriod;
    private String residence;
    private String maritalStatus;
    private String job;
    private String status = "처리중";
    
    public TempPet(int abNumber, int mNumber, String residence, String maritalStatus, String job) {
		// super();
		this.abNumber = abNumber;
		this.mNumber = mNumber;
		this.residence = residence;
		this.maritalStatus = maritalStatus;
		this.job = job;
	}
    
	public int gettNumber() {
		return tNumber;
	}
	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
	}
	public int getAbNumber() {
		return abNumber;
	}
	public void setAbNumber(int abNumber) {
		this.abNumber = abNumber;
	}
	public int getmNumber() {
		return mNumber;
	}
	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}
	public Date getTempDate() {
		return tempDate;
	}
	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}
	public int getTempPeriod() {
		return tempPeriod;
	}
	public void setTempPeriod(int tempPeriod) {
		this.tempPeriod = tempPeriod;
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
