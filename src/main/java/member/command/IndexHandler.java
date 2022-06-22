package member.command;

import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		//req.setAttribute("hello", "안녕하세요!");
		//return "/WEB-INF/view/login/loginForm.jsp";
		return "/WEB-INF/view/main/index.jsp";
	}

}
