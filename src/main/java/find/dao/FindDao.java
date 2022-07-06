package find.dao;

import find.form.FindInForm;
import find.form.FindIndexForm;
import find.form.FindWriteForm;
import find.model.Find;

import jdbc.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindDao {

    public List<FindIndexForm> selectIndex(Connection conn, int start, int end) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * FROM"
                + " (select ROWNUM as NUM, K.* FROM"
                + " (SELECT F.*, M.NAME"
                + " from FINDANIMALBOARD F, MEMBER M"
                + " where F.MNUMBER = M.MNUMBER"
                + " order by FANUMBER DESC) K)"
                + " where NUM between ? and ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start + 1);
            pstmt.setInt(2, start + end);
            rs = pstmt.executeQuery();

            List<FindIndexForm> result = new ArrayList<>();

            while (rs.next()) {
                result.add(convertFind(rs));
            }

            return result;
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public FindInForm selectOneAnimal(Connection conn, int faNumber) {
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "select F.*, M.NAME from FINDANIMALBOARD F inner join MEMBER M on F.MNUMBER = M.MNUMBER where FANUMBER = " + faNumber;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            return makeInForm(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    private FindInForm makeInForm(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int faNumber = rs.getInt("FANUMBER");
            int mNumber = rs.getInt("MNUMBER");
            String name = rs.getString("NAME");
            String species = rs.getString("SPECIES");
            String kind = rs.getString("KIND");
            String location = rs.getString("LOCATION");
            String animalState = rs.getString("ANIMALSTATE");
            String imgPath = rs.getString("IMGPATH");
            Date wrTime = rs.getDate("WRTIME");
            String title = rs.getString("TITLE");
            String content = rs.getString("CONTENT");

            return new FindInForm(faNumber, mNumber, name, species, kind, location, animalState, imgPath, wrTime, title, content);
        }
        return null;
    }

    private FindIndexForm convertFind(ResultSet rs) throws SQLException {
        int faNumber = rs.getInt("FANUMBER");
        String name = rs.getString("NAME");
        String species = rs.getString("SPECIES");
        String kind = rs.getString("KIND");
        String location = rs.getString("LOCATION");
        String state = rs.getString("ANIMALSTATE");
        String img = rs.getString("IMGPATH");
        Date wrTime = rs.getDate("WRTIME");
        String title = rs.getString("TITLE");

        return new FindIndexForm(faNumber, name, species, kind, location, state, img, wrTime, title);
    }

    public int selectCount(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from FINDANIMALBOARD");

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 0;
        } catch(SQLException e) {
            throw new RuntimeException();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    public Find insert(Connection conn, Find find) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into FINDANIMALBOARD "
                    + "(mnumber, species, kind, location, imgPath, title, content) "
                    + "values (?,?,?,?,?,?,?)");
            pstmt.setInt(1, find.getmNumber());
            pstmt.setString(2, find.getSpecies());
            pstmt.setString(3, find.getKind());
            pstmt.setString(4, find.getLocation());
            pstmt.setString(5, find.getImgPath());
            pstmt.setString(6, find.getTitle());
            pstmt.setString(7, find.getContent());
            pstmt.executeUpdate();

            return new Find(find.getFaNumber(), find.getmNumber(), find.getSpecies(), find.getKind(), find.getLocation(), find.getAnimalState(), find.getImgPath(), find.getWrTime(), find.getTitle(), find.getContent());
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    public Find update(Connection conn, Find find) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("update FINDANIMALBOARD set"
                    + " species = ?, kind = ?, location = ?, imgPath = ?, title = ?, content = ?, wrTime = sysdate"
                    + " where faNumber = ?");
            pstmt.setString(1, find.getSpecies());
            pstmt.setString(2, find.getKind());
            pstmt.setString(3, find.getLocation());
 			pstmt.setString(4, find.getImgPath());
            pstmt.setString(5, find.getTitle());
            pstmt.setString(6, find.getContent());
            pstmt.setInt(7, find.getFaNumber());
            pstmt.executeUpdate();

            return new Find(find.getFaNumber(), find.getmNumber(), find.getSpecies(), find.getKind(), find.getLocation(), find.getAnimalState(), find.getImgPath(), find.getWrTime(), find.getTitle(), find.getContent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    public void delete(Connection conn, int faNumber) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("delete from FINDANIMALBOARD where faNumber = ?");
            pstmt.setInt(1, faNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    public FindWriteForm selectFindWriteForm(Connection conn, int faNumber) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select *"
                + " from FINDANIMALBOARD"
                + " where FANUMBER = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, faNumber);
            rs = pstmt.executeQuery();
            FindWriteForm fwf = null;

            while (rs.next()) {
                fwf = findFindWriteForm(rs);
            }

            return fwf;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    private FindWriteForm findFindWriteForm(ResultSet rs) throws SQLException {
        int faNumber = rs.getInt("FANUMBER");
        int mNumber = rs.getInt("MNUMBER");
        String species = rs.getString("SPECIES");
        String kind = rs.getString("KIND");
        String location = rs.getString("LOCATION");
        String img = rs.getString("IMGPATH");
        String title = rs.getString("TITLE");
        String content = rs.getString("CONTENT");

        return new FindWriteForm(faNumber, mNumber, species, kind, location, img, title, content);
    }
}