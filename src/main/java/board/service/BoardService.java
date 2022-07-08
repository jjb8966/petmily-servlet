package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import auth.service.User;
import board.dao.BoardDao;
import board.exception.BoardNotFoundException;
import board.exception.PermissionDeniedException;
import board.form.BoardPage;
import board.form.ModifyRequest;
import board.form.ReadBoardForm;
import board.form.WriteBoardForm;
import board.model.Board;
import jdbc.connection.ConnectionProvider;

public class BoardService {
	
	private BoardDao boardDao = new BoardDao();
	
	public void write(WriteBoardForm req) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			conn.setAutoCommit(false);

			Board board = toBoard(req);
			boardDao.insert(conn, board);

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
	
	public ReadBoardForm getBoard(int bNumber) {
		try (Connection conn = ConnectionProvider.getConnection()){
			ReadBoardForm readBoardForm = boardDao.selectByContent(conn, bNumber);
			
			if (readBoardForm == null) {
				throw new BoardNotFoundException(); 
			}
			
			return new ReadBoardForm(
					readBoardForm.getbNumber(), 
					readBoardForm.getmNumber(), 
					readBoardForm.getName(), 
					readBoardForm.getKindOfBoard(), 
					readBoardForm.getTitle(), 
					readBoardForm.getContent(), 
					readBoardForm.getWrTime(),
					readBoardForm.getCheckPublic()); 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modify(ModifyRequest modReq) {
		try (Connection conn = ConnectionProvider.getConnection()){
			
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
			throw new RuntimeException(e);
		} catch (PermissionDeniedException | BoardNotFoundException e) {
			throw e;
		}
	}
	
	private boolean canModify(int mNumber, ReadBoardForm readBoardForm) {
		return readBoardForm.getmNumber() == mNumber;
	}
	
	private int size = 5;

	public BoardPage getBoardPage(int pageNum, String kindOfBoard) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			conn.setAutoCommit(false);
			
			int total = boardDao.selectCount(conn, kindOfBoard);
			List<ReadBoardForm> content = boardDao.select(conn, (pageNum - 1) * size, size, kindOfBoard);

			conn.commit();
			
			return new BoardPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int bNumber, User authUser) {
		try (Connection conn = ConnectionProvider.getConnection()){
			
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
			throw new RuntimeException(e);
		} catch (PermissionDeniedException | BoardNotFoundException e) {
			throw e;
		} 
	}
}
