package abandoned_animal.command;

import abandoned_animal.form.AbandonedAnimalDetailForm;
import abandoned_animal.form.AbandonedAnimalPage;
import abandoned_animal.form.DonateSubmitForm;
import abandoned_animal.service.DonateService;
import auth.service.User;
import member.service.JoinRequest;
import mvc.command.CommandHandler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonateAbandonedAnimalHandler implements CommandHandler {

    private DonateService donateService = new DonateService();
	private static final String FORM_VIEW = "/WEB-INF/view/abandoned_animal/donateSubmitForm.jsp";

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
	
	// get
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	// post
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		// HttpSession 존재하면 현재 HttpSession 반환. 존재X : 새로 생성하지 않고 null 반환
		HttpSession session = req.getSession(false); 
		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;
		int mNumber = user.getmNumber();
		
		System.out.println(user);
		System.out.println(mNumber);
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

//		Map<String, Boolean> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
//
//		if ((Object)donateSubmitForm.getDonaSum() == null) {
//			errors.put("donaSum", Boolean.TRUE);
//		}
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}


		donateService.donate(donateSubmitForm);

		return "/WEB-INF/view/abandoned_animal/submitSuccess.jsp";
	}
}

// 	@Override
// 	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
// 		String abNumberVal = req.getParameter("abNumber");
// 		int abNumber = 1;
// 
// 		if (abNumberVal != null) {
// 			abNumber = Integer.parseInt(abNumberVal);
// 		}
// 
// 		AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
// 		req.setAttribute("detailForm", detailForm);
// 
// 		return "/WEB-INF/view/abandoned_animal/donateAbandonedAnimal.jsp";
// 		
// 		}
// 
// 		private String processForm(HttpServletRequest req, HttpServletResponse res) {
// 			return FROM_VIEW;
// 		}
// 		
// 		private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
// 			
// 		}
//}
