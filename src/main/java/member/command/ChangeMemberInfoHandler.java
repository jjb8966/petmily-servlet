package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.exception.MemberNotFoundException;
import member.form.MemberInfo;
import member.model.Member;
import member.service.MemberService;
import mvc.command.CommandHandler;

public class ChangeMemberInfoHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/member/changeMemberInfo.jsp";
	private MemberService memberService = new MemberService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		User user = (User)req.getSession().getAttribute("authUser");
		String id = user.getId();

        MemberInfo memberInfo = memberService.findById(id);
        req.setAttribute("memberInfo", memberInfo);	
		return FORM_VIEW;
	}


	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		String id = user.getId();

        MemberInfo memberInfo = memberService.findById(id);

        req.setAttribute("memberInfo", memberInfo);	

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String email= req.getParameter("email");
		String phone = req.getParameter("phone");
		Member newMemberInfo =new Member(pw,name,email,phone,id);

			
		if (!errors.isEmpty()) {
			
			return FORM_VIEW;
		}
		
		try {
			memberService.changeMemberInfo(user.getId(), newMemberInfo);
			User newUser=new User(user.getId(),name);
			
	        MemberInfo updateInfo = memberService.findById(id);
	        req.setAttribute("memberInfo", updateInfo);
	        
			req.getSession().setAttribute("authUser", newUser);
			return "/WEB-INF/view/member/mypage.jsp";
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
