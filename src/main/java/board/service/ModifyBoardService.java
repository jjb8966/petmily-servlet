package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.dao.BoardDao;
import board.form.ModifyRequest;
import board.form.ReadBoardForm;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyBoardService {

	private BoardDao boardDao = new BoardDao();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ReadBoardForm readBoardForm = boardDao.selectByContent(conn, modReq.getbNumber());
			
			if (readBoardForm == null) {
				throw new BoardNotFoundException();
			}
			
			if (!canModify(modReq.getmNumber(), readBoardForm)) {
				throw new PermissionDeniedException();
			}
			
			boardDao.update(
					conn, 
					modReq.getbNumber(), 
					modReq.getTitle(), 
					modReq.getContent(),
					modReq.getCheckPublic());
			
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
