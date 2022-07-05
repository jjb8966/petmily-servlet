package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.form.ReadBoardForm;
import board.service.BoardNotFoundException;
import board.service.ReadBoardService;
import mvc.command.CommandHandler;


public class ReadBoardHandler implements CommandHandler {

	private ReadBoardService readBoardService = new ReadBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String bNumberVal = req.getParameter("bNumber");
		int bNumber = Integer.parseInt(bNumberVal);

		try {
			ReadBoardForm readBoardForm = readBoardService.getBoard(bNumber);
			req.setAttribute("readBoardForm", readBoardForm);

			return "/WEB-INF/view/board/readBoardContent.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);

			return null;
		}
	}

}