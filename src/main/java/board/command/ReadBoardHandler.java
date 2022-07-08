package board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.exception.BoardNotFoundException;
import board.form.ReadBoardForm;
import board.form.ReplyForm;
import board.service.BoardReplyService;
import board.service.BoardService;
import mvc.command.CommandHandler;


public class ReadBoardHandler implements CommandHandler {

	private BoardService boardService = new BoardService();
	private BoardReplyService boardReplyService = new BoardReplyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String bNumberVal = req.getParameter("bNumber");
		int bNumber = Integer.parseInt(bNumberVal);

		try {
			ReadBoardForm readBoardForm = boardService.getBoard(bNumber);
			List<ReplyForm> replies = boardReplyService.getReplies(bNumber);
			readBoardForm.setReplies(replies);
			
			req.setAttribute("readBoardForm", readBoardForm);

			return "/WEB-INF/view/board/readBoardContent.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);

			return null;
		}
	}

}