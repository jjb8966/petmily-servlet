package member.command;

import abandoned_animal.service.AbandonedAnimalPage;
import auth.service.LoginFailException;
import auth.service.User;
import member.dao.MemberDao;
import member.form.MemberInfo;
import member.service.MemberService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MypageHandler implements CommandHandler {

    private MemberService memberService = new MemberService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false);

        Object userObj = session.getAttribute("authUser");
        User user = (User) userObj;
        String id = user.getId();

        MemberInfo memberInfo = memberService.findById(id);

        req.setAttribute("memberInfo", memberInfo);

        return "/WEB-INF/view/member/mypage.jsp";
    }
}
