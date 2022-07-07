package board.command;

import board.form.BoardPage;
import board.service.BoardService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBoardListHandler implements CommandHandler {

    private BoardService boardService = new BoardService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String pbNumberVal = req.getParameter("pbNumber");
        String kindOfBoard = req.getParameter("kindOfBoard");

        int pbNumber = 1;

        if (pbNumberVal != null) {
            pbNumber = Integer.parseInt(pbNumberVal);
        }

        BoardPage boardPage = boardService.getBoardPage(pbNumber, kindOfBoard);
        req.setAttribute("boardPage", boardPage);
        req.setAttribute("kindOfBoard", kindOfBoard);

        return "/WEB-INF/view/board/boardList.jsp";
    }
}