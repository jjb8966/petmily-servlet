package member.command;

import member.exception.DuplicateIdException;
import member.form.JoinRequest;
import member.service.MemberService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/login/joinForm.jsp";
	private MemberService memberService = new MemberService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ParseException {
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
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setPw(req.getParameter("pw"));
		joinReq.setConfirmPw(req.getParameter("confirmPw"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setBirth(Date.valueOf(req.getParameter("birth"))); //���
		joinReq.setGender(req.getParameter("gender"));
		joinReq.setEmail(req.getParameter("email"));
		joinReq.setPhone(req.getParameter("phone"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String pw = joinReq.getPw();
		String confirmPw = joinReq.getConfirmPw();

		if (!isPwEqualToConfirm(pw, confirmPw)) {
			errors.put("notMatch", Boolean.TRUE);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			memberService.join(joinReq);
			return "/WEB-INF/view/login/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private boolean isPwEqualToConfirm(String pw, String confirmPw) {
		return pw != null && pw.equals(confirmPw);
	}
}
