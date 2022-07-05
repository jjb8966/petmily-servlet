package abandoned_animal.command;

import abandoned_animal.form.DonateSubmitForm;
import abandoned_animal.service.DonateService;
import auth.service.User;
import mvc.command.CommandHandler;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonateAbandonedAnimalHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/abandoned_animal/donateSubmitForm.jsp";
	private DonateService donateService = new DonateService();

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
		HttpSession session = req.getSession(false);
		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;
		
		int mNumber = user.getmNumber();
		String abNumberVal = req.getParameter("abNumber");
		int abNumber = 1;

		if (abNumberVal != null) {
			abNumber = Integer.parseInt(abNumberVal);
		}

		int donaSum = Integer.parseInt(req.getParameter("donaSum"));
		String bank = req.getParameter("bank");
		String accountHolder = req.getParameter("accountHolder");
		String accountNumber = req.getParameter("accountNumber");

		DonateSubmitForm donateSubmitForm = new DonateSubmitForm(abNumber, mNumber, donaSum, bank, accountHolder, accountNumber);
		donateService.donate(donateSubmitForm);

		return "/WEB-INF/view/abandoned_animal/submitSuccess.jsp";
	}
}