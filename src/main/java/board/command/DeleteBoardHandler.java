package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.exception.BoardNotFoundException;
import board.exception.PermissionDeniedException;
import board.service.BoardService;
import mvc.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	private BoardService boardService = new BoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String bNumberVal = req.getParameter("bNumber");
		int bNumber = Integer.parseInt(bNumberVal);

		try {
			boardService.delete(bNumber, authUser);
			
			return "/WEB-INF/view/board/deleteSuccess.jsp";
		} catch (BoardNotFoundException | PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		} 
	}
}
