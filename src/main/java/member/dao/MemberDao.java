package member.dao;

import jdbc.JdbcUtil;
import member.model.Member;

import java.sql.*;

public class MemberDao {

	public Member selectById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select * from member where ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(
						rs.getInt("MNUMBER"),
						rs.getString("ID"),
						rs.getString("PW"),
						rs.getString("NAME"),
						rs.getDate("BIRTH"),
						rs.getString("GENDER"),
						rs.getString("EMAIL"),
						rs.getString("PHONE"),
						rs.getString("GRADE")
				);
			}

			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Member selectBymNumber(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where MNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();
			Member member = null;

			if (rs.next()) {
				member = new Member(rs.getString("ID"), rs.getString("PW"), rs.getString("NAME"), rs.getDate("BIRTH"),
						rs.getString("GENDER"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getInt("MNUMBER"));
			}

			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, Member mem) {
		PreparedStatement pstmt = null;

		String sql = "insert into MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE) values (?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getName());
			pstmt.setDate(4, mem.getBirth());
			pstmt.setString(5, mem.getGender());
			pstmt.setString(6, mem.getEmail());
			pstmt.setString(7, mem.getPhone());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Member member) {
		PreparedStatement pstmt = null;

		String sql = "update MEMBER set PW = ?, NAME = ?, PHONE = ?, EMAIL = ? where ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;

		String sql = "delete MEMBER where MNUMBER=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public String selectName(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select NAME from MEMBER where MNUMBER = ?";

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
}
