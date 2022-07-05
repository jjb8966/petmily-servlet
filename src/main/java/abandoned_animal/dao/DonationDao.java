package abandoned_animal.dao;

import jdbc.JdbcUtil;
import java.sql.*;
import abandoned_animal.model.Donation;

public class DonationDao {

	public void insert(Connection conn, Donation donation) {
		PreparedStatement pstmt = null;
		
		String sql = "insert into DONATION (DNUMBER, ABNUMBER, MNUMBER, DONASUM, BANK, ACCOUNTHOLDER, ACCOUNTNUMBER) values (?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, donation.getdNumber());
			pstmt.setInt(2, donation.getAbNumber());
			pstmt.setInt(3, donation.getmNumber());
			pstmt.setInt(4, donation.getDonaSum());
			pstmt.setString(5, donation.getBank());
			pstmt.setString(6, donation.getAccountHolder());
			pstmt.setString(7, donation.getAccountNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}