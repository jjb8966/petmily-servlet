package abandoned_animal.service;

import java.sql.Connection;
import java.sql.SQLException;

import abandoned_animal.dao.AbandonedAnimalDao;
import abandoned_animal.dao.AdoptTempDao;
import abandoned_animal.form.AdoptTempSubmitForm;
import abandoned_animal.model.Adopt;
import abandoned_animal.model.TempPet;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;

public class AdoptTempService {

	private AdoptTempDao adoptTempDao = new AdoptTempDao();
	private AbandonedAnimalDao abandonedAnimalDao = new AbandonedAnimalDao();
	private MemberDao memberDao = new MemberDao();
	
	public void adopt(AdoptTempSubmitForm adoptTempSubmitForm) {

		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// Adopt adopt = new Adopt();
			Adopt adopt = toAdopt(adoptTempSubmitForm);
			adoptTempDao.insertAdopt(conn, adopt);
			conn.commit();
			// return 1;

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
//		try (Connection conn = ConnectionProvider.getConnection()) {
//			Adopt adopt = new Adopt();
//			
//			adoptTempSubmitForm.get
//			
//			adoptTempDao.insertAdopt(conn, adopt);
//
//			return detailForm;
//		
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	};

	public void tempProtect(AdoptTempSubmitForm adoptTempSubmitForm) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// TempPet tempPet = new TempPet();
			TempPet tempPet = toTempPet(adoptTempSubmitForm);
			adoptTempDao.insertTemp(conn, tempPet);
			conn.commit();
			// return 1;

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public String findAnimalName(int abNumber) {
		Connection conn = null;

        try {
			conn = ConnectionProvider.getConnection();
            String animalName = abandonedAnimalDao.selectName(conn, abNumber);
            
            return animalName;
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        } finally {
			JdbcUtil.close(conn);
        }
	}

	public String findMemberName(int mNumber) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			String memberName = memberDao.selectName(conn, mNumber);
			
			return memberName;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Adopt toAdopt(AdoptTempSubmitForm adoptTempSubmitForm) {
		return new Adopt(adoptTempSubmitForm.getmNumber(), adoptTempSubmitForm.getAbNumber(),
				adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
				adoptTempSubmitForm.getJob());
	}

	private TempPet toTempPet(AdoptTempSubmitForm adoptTempSubmitForm) {
		return new TempPet(adoptTempSubmitForm.getAbNumber(), adoptTempSubmitForm.getmNumber(),
				adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
				adoptTempSubmitForm.getJob());
	}

//    public AdoptTempSubmitForm getSubmitForm(int abNumber) {
//        try (Connection conn = ConnectionProvider.getConnection()) {
//        	AdoptTempSubmitForm submitForm = AdoptDao.selectOneAnimal(conn, abNumber);
//
//            return detailForm;
////            List<AbandonedAnimal> content = abandonedAnimalDao.selectIndex(conn, (pageNo - 1) * size, size);
////
////            return new AbandonedAnimalPage(total, pageNo, size, content);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
