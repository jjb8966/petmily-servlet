package abandoned_animal.service;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.model.AbandonedAnimal;
import jdbc.connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListAbandonedAnimalService {

    public static final int SIZE = 4;
    private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();

    public AbandonedAnimalPage getAbandonedAnimalPage(int pageNo) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int total = abandonedAnimalDao.selectCount(conn);
            List<AbandonedAnimal> content = abandonedAnimalDao.selectIndex(conn, (pageNo - 1) * SIZE, SIZE);

            return new AbandonedAnimalPage(total, pageNo, SIZE, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public ArticlePage getArticlePage(int pageNum) {
//        try (Connection conn = ConnectionProvider.getConnection()) {
//            int total = articleDao.selectCount(conn);
//            List<Article> content = articleDao.select(conn, (pageNum - 1) * size, size);
//
//            return new ArticlePage(total, pageNum, size, content);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
