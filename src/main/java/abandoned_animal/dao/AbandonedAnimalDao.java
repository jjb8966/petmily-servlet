package abandoned_animal.dao;

import abandoned_animal.form.AbandonedAnimalDetailForm;
import abandoned_animal.model.AbandonedAnimal;
import jdbc.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbandonedAnimalDao {

	public AbandonedAnimalDetailForm selectOneAnimal(Connection conn, int abNumber) {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from ABANDONEDANIMAL where ABNUMBER =" + abNumber;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			return makeDetailForm(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private AbandonedAnimalDetailForm makeDetailForm(ResultSet rs) {
		try {

			while (rs.next()) {
				int abNumber = rs.getInt("ABNUMBER");
				int sNumber = rs.getInt("SNUMBER");
				int age = rs.getInt("AGE");
				float weight = rs.getFloat("WEIGHT");
				String name = rs.getString("NAME");
				String species = rs.getString("SPECIES");
				String kind = rs.getString("KIND");
				String gender = rs.getString("GENDER");
				String location = rs.getString("LOCATION");
				String uniqueness = rs.getString("UNIQUENESS");
				String description = rs.getString("DESCRIPTION");
				String imgPath = rs.getString("IMGPATH");
				String animalState = rs.getString("ANIMALSTATE");
				Date admissionDate = rs.getDate("ADMISSIONDATE");

				return new AbandonedAnimalDetailForm(abNumber, sNumber, age, weight, name, species, kind, gender,
						location, uniqueness, description, imgPath, animalState, admissionDate);
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AbandonedAnimal> selectIndex(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from"
				+ " (select ROWNUM as NUM, A.ABNUMBER, A.NAME, A.LOCATION, A.ADMISSIONDATE, A.IMGPATH FROM"
				+ " (select * from ABANDONEDANIMAL"
				+ " order by abNumber DESC) A)"
				+ " where NUM between ? and ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start + 1);
			pstmt.setInt(2, start + end);
			rs = pstmt.executeQuery();

			List<AbandonedAnimal> result = new ArrayList<>();

			while (rs.next()) {
				result.add(convertAbandonedAnimal(rs));
			}

			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int selectCount(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from ABANDONEDANIMAL");

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public int selectsNumber(Connection conn, int abNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select SNUMBER from ABANDONEDANIMAL where ABNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, abNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("SNUMBER");
			}

			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private AbandonedAnimal convertAbandonedAnimal(ResultSet rs) {
		try {
			int abNumber = rs.getInt("ABNUMBER");
			String name = rs.getString("NAME");
			String img = rs.getString("IMGPATH");
			String location = rs.getString("LOCATION");
			Date admissionDate = rs.getDate("ADMISSIONDATE");

			return new AbandonedAnimal(abNumber, name, img, location, admissionDate);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String selectName(Connection conn, int abNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select Name from ABANDONEDANIMAL where ABNUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, abNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("NAME");
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}