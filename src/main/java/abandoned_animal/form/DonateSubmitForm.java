package abandoned_animal.form;

public class DonateSubmitForm {

	private int abNumber;
	private int mNumber;
	private int donaSum;
	private String bank;
	private String accountHolder;
	private String accountNumber;

	public DonateSubmitForm(int abNumber, int mNumber, int donaSum, String bank, String accountHolder,
			String accountNumber) {
		this.abNumber = abNumber;
		this.mNumber = mNumber;
		this.donaSum = donaSum;
		this.bank = bank;
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
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