package abandoned_animal.dao;

import abandoned_animal.model.AbandonedAnimal;
import abandoned_animal.service.ListAbandonedAnimalService;
import jdbc.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbandonedAnimalDao {

    public List<AbandonedAnimal> selectIndex(Connection conn, int start, int end) throws SQLException {
        PreparedStatement pstmt = null;
//        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT NAME, LOCATION, ADMISSIONDATE, IMGPATH " +
                "FROM " +
                "(SELECT SEQ, NAME, LOCATION, ADMISSIONDATE, IMGPATH " +
                "FROM " +
                "(SELECT ROWNUM AS SEQ, NAME, LOCATION, ADMISSIONDATE, IMGPATH " +
                "FROM " +
                "(SELECT * " +
                "FROM ABANDONEDANIMAL " +
                "ORDER BY ADMISSIONDATE ))" +
                "WHERE SEQ >= ? )" +
                "WHERE ROWNUM <= ?;";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, ListAbandonedAnimalService.SIZE);
            rs = pstmt.executeQuery();

//            statement = conn.createStatement();
//            rs = statement.executeQuery("select * from ABANDONEDANIMAL");

            List<AbandonedAnimal> result = new ArrayList<>();

            while (rs.next()) {
                result.add(convertAbandonedAnimal(rs));
            }

            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
//            JdbcUtil.close(statement);
        }
    }

    private AbandonedAnimal convertAbandonedAnimal(ResultSet rs) throws SQLException {
        String name = rs.getString("NAME");
        String img = rs.getString("IMGPATH");
        String location = rs.getString("LOCATION");
        Date admissionDate = rs.getDate("ADMISSIONDATE");

        return new AbandonedAnimal(name, img, location, admissionDate);
//        return new AbandonedAnimal(rs.getInt("article_no"),
//                new Writer(
//                        rs.getString("writer_id"),
//                        rs.getString("writer_name")),
//                rs.getString("title"),
//                toDate(rs.getTimestamp("regdate")),
//                toDate(rs.getTimestamp("moddate")),
//                rs.getInt("read_cnt"));
    }

    public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from ABANDONEDANIMAL");

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 0;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }
}
