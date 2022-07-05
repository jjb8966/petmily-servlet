package abandoned_animal.model;

import java.sql.Date;

public class VolunteerApply {

	private int vaNumber;
	private int mNumber;
	private Date volunteerStartDay;
	private int volunteerPeriod;
	private int sNumber;

	public VolunteerApply(int mNumber, Date volunteerStartDay, int volunteerPeriod, int sNumber) {
		this.mNumber = mNumber;
		this.volunteerStartDay = volunteerStartDay;
		this.volunteerPeriod = volunteerPeriod;
		this.sNumber = sNumber;
	}

	public int getVaNumber() {
		return vaNumber;
	}

	public void setVaNumber(int vaNumber) {
		this.vaNumber = vaNumber;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public Date getVolunteerStartDay() {
		return volunteerStartDay;
	}

	public void setVolunteerStartDay(Date volunteerStartDay) {
		this.volunteerStartDay = volunteerStartDay;
	}

	public int getVolunteerPeriod() {
		return volunteerPeriod;
	}

	public void setVolunteerPeriod(int volunteerPeriod) {
		this.volunteerPeriod = volunteerPeriod;
	}

	public int getsNumber() {
		return sNumber;
	}

	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}
}
