package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.BoardNotFoundException;
import board.service.DeleteBoardService;
import board.service.PermissionDeniedException;
import mvc.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	private DeleteBoardService deleteService = new DeleteBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String bNumberVal = req.getParameter("bNumber");
		int bNumber = Integer.parseInt(bNumberVal);

		try {
			deleteService.delete(bNumber, authUser);
			
			return "/WEB-INF/view/board/deleteSuccess.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			
			return null;
		}
	}
}
