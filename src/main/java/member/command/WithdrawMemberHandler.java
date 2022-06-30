package member.command;

import auth.service.User;
import member.service.MemberService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class WithdrawMemberHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/member/withdrawForm.jsp";
    private MemberService memberService = new MemberService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
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
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String pw = req.getParameter("pw");
        String confirmPw = req.getParameter("confirmPw");
        User user = (User) req.getSession().getAttribute("authUser");
        int mNumber = user.getMNumber();

        Map<String, Boolean> errors = new HashMap<>();

        if (isEmpty(pw)) {
            errors.put("pw", Boolean.TRUE);
        }

        if (isEmpty(confirmPw)) {
            errors.put("confirmPw", Boolean.TRUE);
        }

        if (!isEmpty(pw) && !isEmpty(confirmPw)) {
            if (!isPwEqualToConfirm(pw, confirmPw)) {
                errors.put("notMatch", Boolean.TRUE);
            } else if (!isCorrectPw(mNumber, pw)) {
                errors.put("notCorrect", Boolean.TRUE);
            }
        }

        req.setAttribute("errors", errors);

        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }

        memberService.withdraw(mNumber);
        req.getSession().invalidate();

        return "/WEB-INF/view/member/withdrawSuccess.jsp";
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean isPwEqualToConfirm(String pw, String confirmPw) {
        return pw != null && pw.equals(confirmPw);
    }

    private boolean isCorrectPw(int mNumber, String pw) {
        return memberService.checkPwCorrect(mNumber, pw);
    }
}
