package find.service;

import find.dao.FindDao;
import find.form.FindInForm;
import find.form.FindIndexForm;
import find.form.FindPageForm;
import find.form.FindWriteForm;

import jdbc.connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListFindService {

    private final int size = 6;

    private FindDao FindDao = new FindDao();

    public FindPageForm getFindPage(int pageNo) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            conn.setAutoCommit(false);

            int total = FindDao.selectCount(conn);
            List<FindIndexForm> content = FindDao.selectIndex(conn, (pageNo - 1) * size, size);

            conn.commit();

            return new FindPageForm(total, pageNo, size, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public FindInForm getInForm(int faNumber) {
        try (Connection conn = ConnectionProvider.getConnection()) {
        	FindInForm inForm = FindDao.selectOneAnimal(conn, faNumber);

            return inForm;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FindWriteForm getFind(int faNumber, boolean increase) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            conn.setAutoCommit(false);

            int total = FindDao.selectCount(conn);
            FindWriteForm content = FindDao.selectFindWriteForm(conn, faNumber);

            conn.commit();

            return content;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}