package member.service;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) throws ParseException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, userId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			if (!member.matchPw(curPwd)) {
				throw new InvalidPasswordException();
			}
			member.changePw(newPwd);
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
