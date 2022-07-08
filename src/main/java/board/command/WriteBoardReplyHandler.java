package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.form.WriteReplyForm;
import board.service.BoardReplyService;
import mvc.command.CommandHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WriteBoardReplyHandler implements CommandHandler {

	private BoardReplyService boardReplyService = new BoardReplyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession(false).getAttribute("authUser");
		String bNumberVal = req.getParameter("bNumber");
		String kindOfBoardOrigin = req.getParameter("kindOfBoard");
		String kindOfBoard = URLEncoder.encode(kindOfBoardOrigin, StandardCharsets.UTF_8);

		int bNumber = Integer.parseInt(bNumberVal);
		int mNumber = authUser.getmNumber();
		String reply = req.getParameter("reply");

		WriteReplyForm writeReplyForm = new WriteReplyForm(bNumber, mNumber, reply);

		boardReplyService.writeReply(writeReplyForm);
		res.sendRedirect("/board/read.do?kindOfBoard="+ kindOfBoard + "&bNumber=" + bNumber);

		return null;
	}
}
