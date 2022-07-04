package auth.service;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class LoginService {

	private MemberDao memberDao = new MemberDao();

	public User login(String id, String pw) throws ParseException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				throw new LoginFailException();
			}
			if (!member.matchPw(pw)) {
				throw new LoginFailException();
			}
			return new User(member.getmNumber(), member.getId(), member.getName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
