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

	public Board insert(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "insert into board (mNumber, kindOfBoard, title, content, imgPath, video, wrTime, checkPublic)"
					+ "values (?,?,?,?,null,null,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getmNumber());
			pstmt.setString(2, board.getKindOfBoard());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getCheckPublic());
			int insertedCount = pstmt.executeUpdate();

			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public List<ReadBoardForm> select(Connection conn, int startRow, int size, String kindOfBoard) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *" + " from (select rownum as num, a.*" + " from (select * from"
				+ " member m inner join board b" + " on m.mnumber = b.mnumber" + " where b.kindofboard = ?"
				+ " order by b.bnumber desc) a)" + " where num between ? and ?";

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
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private ReadBoardForm convertBoard(ResultSet rs) throws SQLException {

		return new ReadBoardForm(rs.getInt("bNumber"), rs.getInt("mNumber"), rs.getString("name"),
				rs.getString("kindOfBoard"), rs.getString("title"), rs.getString("content"), rs.getDate("wrTime"),
				rs.getString("checkPublic"));
	}

	public int selectCount(Connection conn, String kindOfBoard) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board where kindOfBoard = '" + kindOfBoard + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public ReadBoardForm selectByContent(Connection conn, int bNumber) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select b.*, m.id, m.name from board b, member m where b.mnumber = m.mnumber and bNumber = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNumber);
			rs = pstmt.executeQuery();
			ReadBoardForm readBoardForm = null;
			if (rs.next()) {
				readBoardForm = convertBoard(rs);
			}

			return readBoardForm;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, int bNumber, String title, String content, String checkPublic) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update board set title = ?, content = ?, checkPublic = ?, wrTime = sysdate " + "where bNumber = ?")) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, checkPublic);
			pstmt.setInt(4, bNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int delete(Connection conn, int bNumber) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from board where bNumber = ?")) {
			pstmt.setInt(1, bNumber);

			return pstmt.executeUpdate();
		}
	}
}