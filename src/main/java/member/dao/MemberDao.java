package member.dao;

import jdbc.JdbcUtil;
import member.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class MemberDao {

    public Member selectById(Connection conn, String id) throws SQLException, ParseException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "select * from member where ID = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            Member member = null;
            if (rs.next()) {
                member = new Member(rs.getInt("MNUMBER"),
                        rs.getString("ID"),
                        rs.getString("PW"),
                        rs.getString("NAME"),
                        rs.getDate("BIRTH"),
                        rs.getString("GENDER"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE"),
                        rs.getString("GRADE"));
                member.setmNumber(rs.getInt("MNUMBER"));
            }
            return member;

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public Member selectBymNumber(Connection conn, int mNumber) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "select * from member where MNUMBER = ?");
            pstmt.setInt(1, mNumber);
            rs = pstmt.executeQuery();
            Member member = null;

            if (rs.next()) {
                member = new Member(rs.getInt("MNUMBER"),
                        rs.getString("ID"),
                        rs.getString("PW"),
                        rs.getString("NAME"),
                        rs.getDate("BIRTH"),
                        rs.getString("GENDER"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE"),
                        rs.getString("GRADE"));
                member.setmNumber(rs.getInt("MNUMBER"));
            }

            return member;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public void insert(Connection conn, Member mem) throws SQLException, ParseException {
        try (PreparedStatement pstmt = conn.prepareStatement("insert into MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE) values (?,?,?,?,?,?,?)")) {
            pstmt.setString(1, mem.getId());
            pstmt.setString(2, mem.getPw());
            pstmt.setString(3, mem.getName());
            pstmt.setDate(4, mem.getBirth());
            pstmt.setString(5, mem.getGender());
            pstmt.setString(6, mem.getEmail());
            pstmt.setString(7, mem.getPhone());
            pstmt.executeUpdate();
        }
    }

    public void update(Connection conn, Member member) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "update member set name = ?, pw = ? where id = ?")) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(Connection conn, int mNumber) throws SQLException {
        PreparedStatement pstmt = null;

        String sql = "delete MEMBER where MNUMBER=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mNumber);
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
}
