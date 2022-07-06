package abandoned_animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import abandoned_animal.model.VolunteerApply;
import jdbc.JdbcUtil;

public class VolunteerDao {

	public void insert(Connection conn, VolunteerApply volunteerApply) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into VOLUNTEERAPPLY (MNUMBER, VOLUNTEERSTARTDAY, VOLUNTEERPERIOD, SNUMBER) values (?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, volunteerApply.getmNumber());
			pstmt.setDate(2, volunteerApply.getVolunteerStartDay());
			pstmt.setInt(3, volunteerApply.getVolunteerPeriod());
			pstmt.setInt(4, volunteerApply.getsNumber());
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
