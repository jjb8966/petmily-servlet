package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.BoardDao;
import board.form.ReadBoardForm;
import jdbc.connection.ConnectionProvider;

public class ListBoardService {

	private BoardDao boardDao = new BoardDao();
	private int size = 5;

	public BoardPage getBoardPage(int pageNum, String kindOfBoard) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectCount(conn, kindOfBoard);
			
			System.out.println(pageNum);
			
			List<ReadBoardForm> content = boardDao.select(conn, (pageNum - 1) * size, size, kindOfBoard);

			return new BoardPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
