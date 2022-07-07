package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.BoardReply;
import jdbc.JdbcUtil;

public class BoardReplyDao {

    public List<BoardReply> selectReplies(Connection conn, int bNumber) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardReply> boardReplies = new ArrayList<>();

        String sql = "select * from BOARDREPLY where BNUMBER = ? order by WRTIME";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bNumber);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                BoardReply boardReply = new BoardReply(
                    rs.getInt("BRNUMBER"),
                    rs.getInt("BNUMBER"),
                    rs.getInt("MNUMBER"),
                    rs.getString("REPLY"),
                    rs.getDate("WRTIME")
                );
                
                boardReplies.add(boardReply);
            }
            
            return boardReplies;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public void insert(Connection conn, BoardReply boardReply) {
        PreparedStatement pstmt = null;

        String sql = "insert into BOARDREPLY(BNUMBER, MNUMBER, REPLY) values (?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardReply.getbNumber());
            pstmt.setInt(2, boardReply.getmNumber());
            pstmt.setString(3, boardReply.getReply());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(pstmt);
        }
    }


    public void delete(Connection conn, int brNumber) {
        PreparedStatement pstmt = null;

        String sql = "delete from BOARDREPLY where BRNUMBER = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, brNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
}
