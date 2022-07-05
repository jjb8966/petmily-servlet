package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.dao.VolunteerDao;
import abandoned_animal.form.VolunteerApplySubmitForm;
import abandoned_animal.model.VolunteerApply;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class VolunteerService {

	private VolunteerDao volunteerDao = new VolunteerDao();
	private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
	private MemberDao memberDao = new MemberDao();

	public int findsNumber(int abNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			int sNumber = abandonedAnimalDao.selectsNumber(conn, abNumber);

			return sNumber;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public void volunteer(VolunteerApplySubmitForm volunteerApplySubmitForm) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			VolunteerApply volunteerApply = new VolunteerApply(volunteerApplySubmitForm.getmNumber(),
					volunteerApplySubmitForm.getVolunteerStartDay(), volunteerApplySubmitForm.getVolunteerPeriod(),
					volunteerApplySubmitForm.getsNumber());
			volunteerDao.insert(conn, volunteerApply);
		} catch (SQLException e) {
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
			String MemberName = memberDao.selectName(conn, mNumber);

			return MemberName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public String findMemberBirth(int mNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			String MemberBirth = memberDao.selectBirth(conn, mNumber);

			return MemberBirth;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public String findMemberPhone(int mNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			String MemberPhone = memberDao.selectPhone(conn, mNumber);

			return MemberPhone;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public String findMemberEmail(int mNumber) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			String MemberEmail = memberDao.selectEmail(conn, mNumber);

			return MemberEmail;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	/*
	 * public String findMemberInfo(int mNumber) { Connection conn = null;
	 * 
	 * try { conn = ConnectionProvider.getConnection(); String MemberName =
	 * memberDao.selectName(conn, mNumber); String Memberbirth =
	 * memberDao.selectbirth(conn, mNumber);
	 * 
	 * return MemberName; } catch (SQLException e) { throw new RuntimeException(e);
	 * } finally { JdbcUtil.close(conn); } }
	 */

}
