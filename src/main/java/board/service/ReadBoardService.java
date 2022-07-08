package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.dao.BoardDao;
import board.form.ReadBoardForm;
import jdbc.connection.ConnectionProvider;

public class ReadBoardService {

	private BoardDao boardDao = new BoardDao();
	
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
}
