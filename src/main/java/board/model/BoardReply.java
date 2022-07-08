package board.model;

import java.sql.Date;

public class BoardReply {

	private int brNumber;
	private int bNumber;
	private int mNumber;
	private String reply;
	private Date wrTime;

	public BoardReply(int brNumber, int bNumber, int mNumber, String reply, Date wrTime) {
		this.brNumber = brNumber;
		this.bNumber = bNumber;
		this.mNumber = mNumber;
		this.reply = reply;
		this.wrTime = wrTime;
	}

	public BoardReply(int bNumber, int mNumber, String reply) {
		this.bNumber = bNumber;
		this.mNumber = mNumber;
		this.reply = reply;
	}

	public int getBrNumber() {
		return brNumber;
	}


	public void setBrNumber(int brNumber) {
		this.brNumber = brNumber;
	}


	public int getbNumber() {
		return bNumber;
	}


	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}


	public int getmNumber() {
		return mNumber;
	}


	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public Date getWrTime() {
		return wrTime;
	}


	public void setWrTime(Date wrTime) {
		this.wrTime = wrTime;
	}
	
}