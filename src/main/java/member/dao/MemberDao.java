package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import jdbc.JdbcUtil;
import member.MemberInfo;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException, ParseException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from member where ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("ID"), rs.getString("PW"), rs.getString("NAME"), rs.getDate("BIRTH"),
						rs.getString("GENDER"), rs.getString("EMAIL"), rs.getString("PHONE"));
//						toDate(rs.getTimestamp("regdate")));
				member.setmNumber(rs.getInt("MNUMBER"));
			}
			return member;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, Member mem) throws SQLException, ParseException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"insert into MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE) values (?,?,?,?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getName());
			pstmt.setDate(4, mem.getBirth());
			pstmt.setString(5, mem.getGender());
			pstmt.setString(6, mem.getEmail());
			pstmt.setString(7, mem.getPhone());
			pstmt.executeUpdate();
		}
	}

	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update member set name = ?, pw = ? where id = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		}
	}

	public String selectName(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select Name from MEMBER where MNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("NAME");
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String selectBirth(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select BIRTH from MEMBER where MNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("BIRTH");
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String selectPhone(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select PHONE from MEMBER where MNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("PHONE");
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String selectEmail(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select EMAIL from MEMBER where MNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("EMAIL");
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*
	 * //리팩토링예정 public MemberInfo findMemberInfo(Connection conn, int mNumber) {
	 * Statement stmt = null; ResultSet rs = null;
	 * 
	 * String sql = "select * from MEMBER where MNUMBER =" + mNumber;
	 * 
	 * try { stmt = conn.createStatement(); rs = stmt.executeQuery(sql);
	 * 
	 * return makeDetailForm(rs); } catch (SQLException e) { throw new
	 * RuntimeException(e); } }
	 * 
	 * private MemberInfo makeDetailForm(ResultSet rs) throws SQLException { while
	 * (rs.next()) { String name = rs.getString("NAME"); Date birth =
	 * rs.getDate("BIRTH"); String phone = rs.getString("PHONE"); String email =
	 * rs.getString("EMAIL");
	 * 
	 * return new Member(name, birth, phone, email); } return null; }
	 */
}
