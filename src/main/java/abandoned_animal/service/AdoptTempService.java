package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.dao.AdoptTempDao;
import abandoned_animal.form.AdoptTempSubmitForm;
import abandoned_animal.model.Adopt;
import abandoned_animal.model.TempPet;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class AdoptTempService {

	private AdoptTempDao adoptTempDao = new AdoptTempDao();
	private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
	private MemberDao memberDao = new MemberDao();

	public void adopt(AdoptTempSubmitForm adoptTempSubmitForm) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);

			Adopt adopt = toAdopt(adoptTempSubmitForm);
			adoptTempDao.insertAdopt(conn, adopt);

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);

			TempPet tempPet = toTempPet(adoptTempSubmitForm);
			adoptTempDao.insertTemp(conn, tempPet);

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String findAnimalName(int abNumber) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			String animalName = abandonedAnimalDao.selectName(conn, abNumber);

			return animalName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String findMemberName(int mNumber) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			String memberName = memberDao.selectName(conn, mNumber);

			return memberName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Adopt toAdopt(AdoptTempSubmitForm adoptTempSubmitForm) {
		return new Adopt(adoptTempSubmitForm.getmNumber(), adoptTempSubmitForm.getAbNumber(),
				adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
				adoptTempSubmitForm.getJob());
	}

	private TempPet toTempPet(AdoptTempSubmitForm adoptTempSubmitForm) {
		return new TempPet(adoptTempSubmitForm.getAbNumber(), adoptTempSubmitForm.getmNumber(),
				adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
				adoptTempSubmitForm.getJob());
	}
}
