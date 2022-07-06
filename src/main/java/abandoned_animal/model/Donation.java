package abandoned_animal.model;

public class Donation {

	private int donaPeriod;
	private int dNumber;
	private int abNumber;
	private int mNumber;
	private int donaSum;
	private String bank;
	private String accountHolder;
	private String accountNumber;

	public Donation(int abNumber, int mNumber, int donaSum, String bank, String accountHolder, String accountNumber) {
		this.abNumber = abNumber;
		this.mNumber = mNumber;
		this.donaSum = donaSum;
		this.bank = bank;
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
	}

	public int getDonaPeriod() {
		return donaPeriod;
	}

	public void setDonaPeriod(int donaPeriod) {
		this.donaPeriod = donaPeriod;
	}

	public int getdNumber() {
		return dNumber;
	}

	public void setdNumber(int dNumber) {
		this.dNumber = dNumber;
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

	public int getDonaSum() {
		return donaSum;
	}

	public void setDonaSum(int donaSum) {
		this.donaSum = donaSum;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
