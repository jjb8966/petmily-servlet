package abandoned_animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import abandoned_animal.model.Adopt;
import abandoned_animal.model.TempPet;
import jdbc.JdbcUtil;

public class AdoptTempDao {

	public void insertAdopt(Connection conn, Adopt adopt) {
		PreparedStatement pstmt = null;
		String sql = "insert into ADOPT (MNUMBER, ABNUMBER, RESIDENCE, MARITALSTATUS, JOB) values (?,?,?,?,?)";

		try {
			// insert into~~
			pstmt = conn.prepareStatement(sql);
//		 	pstmt.setString(1, id);
			pstmt.setInt(1, adopt.getmNumber());
			pstmt.setInt(2, adopt.getAbNumber());
			pstmt.setString(3, adopt.getResidence());
			pstmt.setString(4, adopt.getMaritalStatus());
			pstmt.setString(5, adopt.getJob());
//			rs = pstmt.executeQuery();
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void insertTemp(Connection conn, TempPet temp) {
		PreparedStatement pstmt = null;
		String sql = "insert into TEMPPET (ABNUMBER, MNUMBER, RESIDENCE, MARITALSTATUS, JOB, TEMPDATE, TEMPPERIOD) values (?,?,?,?,?, SYSDATE, 2)";
		// insert into~~
		// String sql;

		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
			pstmt.setInt(1, temp.getAbNumber());
			pstmt.setInt(2, temp.getmNumber());
			pstmt.setString(3, temp.getResidence());
			pstmt.setString(4, temp.getMaritalStatus());
			pstmt.setString(5, temp.getJob());
//			rs = pstmt.executeQuery();
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
