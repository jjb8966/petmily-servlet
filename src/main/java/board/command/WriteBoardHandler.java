package board.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.form.WriteBoardForm;
import board.service.WriteBoardService;
import mvc.command.CommandHandler;

public class WriteBoardHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/board/writeBoardForm.jsp";
	private WriteBoardService writeBoardService = new WriteBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		String kindOfBoard = req.getParameter("kindOfBoard");
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteBoardForm writeReq = writeBoardRequest(user, req);
		writeReq.validate(errors);	
		
        writeBoardService.write(writeReq);
        req.setAttribute("kindOfBoard", kindOfBoard);

		return "/WEB-INF/view/board/writeBoardSuccess.jsp";
	}

	private WriteBoardForm writeBoardRequest(User user, HttpServletRequest req) {
		
		return new WriteBoardForm(
				user.getmNumber(), 
				req.getParameter("kindOfBoard"), 
				req.getParameter("title"),
				req.getParameter("content"), 
				req.getParameter("checkPublic"));
	}
}
