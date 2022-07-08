package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.form.ReadBoardForm;
import board.model.Board;
import jdbc.JdbcUtil;

public class BoardDao {

	public Board insert(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into BOARD (MNUMBER, KINDOFBOARD, TITLE, CONTENT, IMGPATH, VIDEO, WRTIME, CHECKPUBLIC)"
				+ "values (?,?,?,?,null,null,SYSDATE,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getmNumber());
			pstmt.setString(2, board.getKindOfBoard());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getCheckPublic());
			pstmt.executeUpdate();
			
			return null;
		} catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<ReadBoardForm> select(Connection conn, int startRow, int size, String kindOfBoard) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select *" 
				+ " from (select ROWNUM as NUM, A.*" 
				+ " from (select * from"
				+ " MEMBER M inner join BOARD B" 
				+ " on M.MNUMBER = B.MNUMBER" 
				+ " where B.KINDOFBOARD = ?"
				+ " order by B.BNUMBER desc) A)" 
				+ " where NUM between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kindOfBoard);
			pstmt.setInt(2, startRow + 1);
			pstmt.setInt(3, startRow + size);
			rs = pstmt.executeQuery();

			List<ReadBoardForm> result = new ArrayList<>();

			while (rs.next()) {
				result.add(convertBoard(rs));
			}

			return result;
		} catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private ReadBoardForm convertBoard(ResultSet rs) throws SQLException {
		return new ReadBoardForm(
				rs.getInt("bNumber"), 
				rs.getInt("mNumber"), 
				rs.getString("name"),
				rs.getString("kindOfBoard"), 
				rs.getString("title"), 
				rs.getString("content"), 
				rs.getDate("wrTime"),
				rs.getString("checkPublic"));
	}

	public int selectCount(Connection conn, String kindOfBoard) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from BOARD where KINDOFBOARD = '" + kindOfBoard + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public ReadBoardForm selectByContent(Connection conn, int bNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select B.*, M.ID, M.NAME from BOARD B, MEMBER M where B.MNUMBER = M.MNUMBER and BNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNumber);
			rs = pstmt.executeQuery();
			ReadBoardForm readBoardForm = null;
			
			if (rs.next()) {
				readBoardForm = convertBoard(rs);
			}

			return readBoardForm;
		} catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, int bNumber, String title, String content, String checkPublic) {
		PreparedStatement pstmt = null;
		String sql = "update BOARD set TITLE = ?, CONTENT = ?, CHECKPUBLIC = ?, WRTIME = SYSDATE " + "where BNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, checkPublic);
			pstmt.setInt(4, bNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int delete(Connection conn, int bNumber) {
		PreparedStatement pstmt = null;
		String sql = "delete from BOARD where BNUMBER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}