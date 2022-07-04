package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.DonationDao;
import abandoned_animal.form.DonateSubmitForm;
import abandoned_animal.model.Donation;
import jdbc.connection.ConnectionProvider;

public class DonateService {

	private DonationDao donationDao = new DonationDao();

	public void donate(DonateSubmitForm donateSubmitForm) {
		Donation donation = new Donation(
				donateSubmitForm.getAbNumber(), 
				donateSubmitForm.getmNumber(),
				donateSubmitForm.getDonaSum(), 
				donateSubmitForm.getBank(), 
				donateSubmitForm.getAccountHolder(),
				donateSubmitForm.getAccountNumber()
				);

		try (Connection conn = ConnectionProvider.getConnection()) {
			donationDao.insert(conn, donation);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}