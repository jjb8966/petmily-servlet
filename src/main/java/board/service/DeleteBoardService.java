package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import board.dao.BoardDao;
import board.form.ReadBoardForm;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteBoardService {

	private BoardDao boardDao = new BoardDao();

	public void delete(int bNumber, User authUser) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ReadBoardForm readBoardForm = boardDao.selectByContent(conn, bNumber);
			
			if (readBoardForm == null) {
				throw new BoardNotFoundException();
			}
			
			if (!canModify(authUser.getmNumber(), readBoardForm)) {
				throw new PermissionDeniedException();
			}
			
			boardDao.delete(conn, bNumber);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(int mNumber, ReadBoardForm readBoardForm) {
		return readBoardForm.getmNumber() == mNumber;
	}
}
