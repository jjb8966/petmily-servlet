package member.service;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.exception.DuplicateIdException;
import member.exception.InvalidPasswordException;
import member.exception.MemberNotFoundException;
import member.form.JoinRequest;
import member.form.MemberInfo;
import member.model.Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class MemberService {

	private MemberDao memberDao = new MemberDao();

	public void changePassword(String userId, String curPwd, String newPwd) throws ParseException {
		try (Connection conn = ConnectionProvider.getConnection()){
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
			throw new RuntimeException(e);
		}
	}

	public void join(JoinRequest joinReq) throws ParseException {
		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, joinReq.getId());
			
			if (member != null) {
				throw new DuplicateIdException();
			}

			memberDao.insert(conn, new Member(joinReq.getId(), joinReq.getPw(), joinReq.getName(), joinReq.getBirth(),
					joinReq.getGender(), joinReq.getEmail(), joinReq.getPhone()));
			
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public MemberInfo findById(String userId) {
		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, userId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			String id = member.getId();
			String pw = member.getPw();
			String name = member.getName();
			Date birth = member.getBirth();
			String gender = member.getGender();
			String email = member.getEmail();
			String phone = member.getPhone();
			String grade = member.getGrade();

			MemberInfo memberInfo = new MemberInfo(id, pw, name, birth, gender, email, phone, grade);
			
			return memberInfo;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void changeMemberInfo(String id, Member member) throws ParseException {
		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);

			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			memberDao.update(conn, member);
			
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
