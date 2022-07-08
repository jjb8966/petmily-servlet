package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.dao.BoardDao;
import board.form.WriteBoardForm;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteBoardService {

	private BoardDao boardDao = new BoardDao();

	public void write(WriteBoardForm req) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Board board = toBoard(req);
			Board savedBoard = boardDao.insert(conn, board);
			
			conn.commit();
            
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Board toBoard(WriteBoardForm req) {

		return new Board(
				req.getmNumber(), 
				req.getKindOfBoard(), 
				req.getTitle(), 
				req.getContent(), 
				req.getCheckPublic());
	}
}
