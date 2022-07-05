package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.dao.AdoptTempDao;
import abandoned_animal.form.AdoptTempSubmitForm;
import abandoned_animal.model.Adopt;
import abandoned_animal.model.TempPet;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class AdoptTempService {

	private AdoptTempDao adoptTempDao = new AdoptTempDao();
	private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
	private MemberDao memberDao = new MemberDao();

	public void adopt(AdoptTempSubmitForm adoptTempSubmitForm) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Adopt adopt = toAdopt(adoptTempSubmitForm);
			adoptTempDao.insertAdopt(conn, adopt);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			TempPet tempPet = toTempPet(adoptTempSubmitForm);
			adoptTempDao.insertTemp(conn, tempPet);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public String findAnimalName(int abNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			String animalName = abandonedAnimalDao.selectName(conn, abNumber);

			return animalName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public String findMemberName(int mNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			String memberName = memberDao.selectName(conn, mNumber);

			return memberName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
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
