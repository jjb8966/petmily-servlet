package board.service;

import board.dao.BoardReplyDao;
import board.form.ReplyForm;
import board.form.WriteReplyForm;
import board.model.BoardReply;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardReplyService {

    private BoardReplyDao boardReplyDao = new BoardReplyDao();
    private MemberDao memberDao = new MemberDao();

    public List<ReplyForm> getReplies(int bNumber) {
        try(Connection conn = ConnectionProvider.getConnection()) {
            List<BoardReply> repliesVal = boardReplyDao.selectReplies(conn, bNumber);
            List<ReplyForm> replies = toReplyForm(conn, repliesVal);

            return replies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ReplyForm> toReplyForm(Connection conn, List<BoardReply> repliesVal) {
        List<ReplyForm> replies = new ArrayList<>();

        for(BoardReply boardReply : repliesVal) {
            Member member = memberDao.selectBymNumber(conn, boardReply.getmNumber());

            int brNumber = boardReply.getBrNumber();
            int mNumber = boardReply.getmNumber();
            String name = member.getName();
            Date wrTime = boardReply.getWrTime();
            String reply = boardReply.getReply();

            replies.add(new ReplyForm(brNumber, mNumber, name, wrTime, reply));
        }

        return replies;
    }

    public void writeReply(WriteReplyForm writeReplyForm) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            BoardReply boardReply = toBoardReply(writeReplyForm);
            boardReplyDao.insert(conn, boardReply);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private BoardReply toBoardReply(WriteReplyForm writeReplyForm) {
        return new BoardReply(writeReplyForm.getbNumber(), writeReplyForm.getmNumber(), writeReplyForm.getReply());
    }

    public void delete(int brNumber) {
        try (Connection conn = ConnectionProvider.getConnection()){

            conn.setAutoCommit(false);

            boardReplyDao.delete(conn, brNumber);

            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
