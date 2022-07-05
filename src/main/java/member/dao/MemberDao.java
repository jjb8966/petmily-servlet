package member.dao;

import jdbc.JdbcUtil;
import member.model.Member;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException, ParseException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select * from member where ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("ID"),
						rs.getString("PW"),
						rs.getString("NAME"),
						rs.getDate("BIRTH"),
						rs.getString("GENDER"),
						rs.getString("EMAIL"),
						rs.getString("PHONE"));
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
		try (PreparedStatement pstmt = conn.prepareStatement("insert into MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE) values (?,?,?,?,?,?,?)")) {
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
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update member set name = ?, pw = ? where id = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		}
	}
	
	public String selectName(Connection conn, int mNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select Name from MEMBER where MNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("NAME");
			}
			
			return null;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
}
