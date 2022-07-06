package find.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;

import find.dao.FindDao;
import find.form.FindWriteForm;
import find.model.Find;

import jdbc.connection.ConnectionProvider;

public class FindService {

	private FindDao findDao = new FindDao();

	public void write(FindWriteForm req) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);

			Find find = toFind(req);
			findDao.insert(conn, find);

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Find toFind(FindWriteForm req) {
		return new Find(req.getmNumber(), req.getSpecies(), req.getKind(), req.getLocation(), req.getImgPath(), req.getTitle(), req.getContent());
	}
	
	public void modify(FindWriteForm modReq) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);

			Find find = toFind(modReq);
			find.setFaNumber(modReq.getFaNumber());
			
			findDao.update(conn, find);

			if (!canModify(modReq.getmNumber(), find)) {
				//throw new PermissionDeniedException();
			}

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/*
		 * catch (PermissionDeniedException e) { JdbcUtil.rollback(conn); throw e; }
		 */
	}
	
	private boolean canModify(int mNumber, Find find) {
		return find.getmNumber() == mNumber;
	}
	
	public void delete(int faNumber, User user) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			
			findDao.delete(conn, faNumber);

			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/*
		 * catch (PermissionDeniedException e) { JdbcUtil.rollback(conn); throw e; }
		 */
	}
}