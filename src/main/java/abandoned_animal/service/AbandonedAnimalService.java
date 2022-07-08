package abandoned_animal.service;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.form.AbandonedAnimalDetailForm;
import abandoned_animal.form.AbandonedAnimalPageForm;
import abandoned_animal.model.AbandonedAnimal;
import jdbc.connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AbandonedAnimalService {

    private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
    private int size = 12;

    public AbandonedAnimalDetailForm getDetailForm(int abNumber) {
        try (Connection conn = ConnectionProvider.getConnection()) {
        	conn.setAutoCommit(false);
        	
            AbandonedAnimalDetailForm detailForm = abandonedAnimalDao.selectOneAnimal(conn, abNumber);

            conn.commit();
            
            return detailForm;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo) {
        try (Connection conn = ConnectionProvider.getConnection()) { 
        	conn.setAutoCommit(false);
        	
            int total = abandonedAnimalDao.selectCount(conn);
            List<AbandonedAnimal> content = abandonedAnimalDao.selectIndex(conn, (pageNo - 1) * size, size);
            
            conn.commit();

            return new AbandonedAnimalPageForm(total, pageNo, size, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}