package member.service;

import jdbc.JdbcUtil;
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

    public void join(JoinRequest joinReq) throws ParseException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            Member member = memberDao.selectById(conn, joinReq.getId());
            if (member != null) {
                JdbcUtil.rollback(conn);
                throw new DuplicateIdException();
            }

            memberDao.insert(conn, new Member(joinReq.getId(), joinReq.getPw(), joinReq.getName(), joinReq.getBirth(), joinReq.getGender(), joinReq.getEmail(), joinReq.getPhone()));
            conn.commit();
        } catch (SQLException e) {
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }

    public MemberInfo findById(String userId) {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();

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
        } finally {
            JdbcUtil.close(conn);
        }
    }

    public void withdraw(int mNumber) {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();

            memberDao.delete(conn, mNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }

    public boolean checkPwCorrect(int mNumber, String pw) {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection();

            Member member = memberDao.selectByMNumber(conn, mNumber);

            if (member.getPw().equals(pw)) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}
