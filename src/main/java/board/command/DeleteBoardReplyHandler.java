package board.command;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.exception.BoardNotFoundException;
import board.exception.PermissionDeniedException;
import board.service.BoardReplyService;
import mvc.command.CommandHandler;

public class DeleteBoardReplyHandler implements CommandHandler {

    private BoardReplyService boardReplyService = new BoardReplyService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return processSubmit(req, res);
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String bNumberVal = req.getParameter("bNumber");
        String kindOfBoardOrigin = req.getParameter("kindOfBoard");
        String brNumberVal = req.getParameter("brNumber");

        int brNumber = Integer.parseInt(brNumberVal);
        String kindOfBoard = URLEncoder.encode(kindOfBoardOrigin, StandardCharsets.UTF_8);

        int bNumber = Integer.parseInt(bNumberVal);

        try {
            boardReplyService.delete(brNumber);
            res.sendRedirect("/board/read.do?kindOfBoard="+ kindOfBoard + "&bNumber=" + bNumber);

            return null;
        } catch (BoardNotFoundException | PermissionDeniedException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);

            return null;
        }
    }
}
