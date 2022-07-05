package board.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.form.ModifyRequest;
import board.form.ReadBoardForm;
import board.service.BoardNotFoundException;
import board.service.ModifyBoardService;
import board.service.PermissionDeniedException;
import board.service.ReadBoardService;
import mvc.command.CommandHandler;

public class ModifyBoardHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/board/modifyForm.jsp";
	private ReadBoardService readService = new ReadBoardService();
	private ModifyBoardService modifyService = new ModifyBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String bNumberVal = req.getParameter("bNumber");
			int bNumber = Integer.parseInt(bNumberVal);
			ReadBoardForm readBoardForm = readService.getBoard(bNumber);

			// 로그인한 사람, 글작성자 비교 후 일치하면 수정
			User authUser = (User) req.getSession().getAttribute("authUser");

			if (!canModify(authUser, readBoardForm)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);

				return null;
			}

			ModifyRequest modReq = new ModifyRequest(
					readBoardForm.getbNumber(), 
					authUser.getmNumber(),
					readBoardForm.getTitle(), 
					readBoardForm.getContent(),
					readBoardForm.getCheckPublic());

			req.setAttribute("modReq", modReq);

			return FORM_VIEW;
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);

			return null;
		}
	}

	private boolean canModify(User authUser, ReadBoardForm readBoardForm) {
		return authUser.getmNumber() == readBoardForm.getmNumber();
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String bNumberVal = req.getParameter("bNumber");
		int bNumber = Integer.parseInt(bNumberVal);
		String kindOfBoard = req.getParameter("kindOfBoard");

		ModifyRequest modReq = new ModifyRequest(
				bNumber, 
				authUser.getmNumber(), 
				req.getParameter("title"),
				req.getParameter("content"), 
				req.getParameter("checkPublic"));
		
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		req.setAttribute("kindOfBoard", kindOfBoard);

		modReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			modifyService.modify(modReq);
			req.setAttribute("kindOfBoard", kindOfBoard);

			return "/WEB-INF/view/board/modifySuccess.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);

			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);

			return null;
		}
	}
}
