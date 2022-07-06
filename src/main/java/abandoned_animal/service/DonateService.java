package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.dao.DonationDao;
import abandoned_animal.form.DonateSubmitForm;
import abandoned_animal.model.Donation;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class DonateService {

	private DonationDao donationDao = new DonationDao();
	private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
	private MemberDao memberDao = new MemberDao();

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
			conn.setAutoCommit(false);
			
			donationDao.insert(conn, donation);
			
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String findAnimalName(int abNumber) {
		
		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			String animalName = abandonedAnimalDao.selectName(conn, abNumber);
			
			conn.commit();
			
			return animalName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public String findMemberName(int mNumber) {

		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			String memberName = memberDao.selectName(conn, mNumber);

			conn.commit();
			
			return memberName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
}