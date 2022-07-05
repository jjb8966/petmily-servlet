package abandoned_animal.command;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abandoned_animal.form.VolunteerApplySubmitForm;
import abandoned_animal.service.VolunteerService;
import auth.service.User;
import member.service.DuplicateIdException;
import mvc.command.CommandHandler;

public class VolunteerAbandonedAnimalHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/abandoned_animal/volunteerAbandonedAnimal.jsp";
	private VolunteerService volunteerService = new VolunteerService();

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
		int abNumber = Integer.parseInt(req.getParameter("abNumber"));
		
		HttpSession session = req.getSession(false);

		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;
		int mNumber = user.getmNumber();

		String animalName = volunteerService.findAnimalName(abNumber);
		String memberName = volunteerService.findMemberName(mNumber);

		if (animalName != null) {
			req.setAttribute("animalName", animalName);
		}
	
	

		if (memberName != null) {
			req.setAttribute("memberName", memberName);
		}
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException {
		int abNumber = Integer.parseInt(req.getParameter("abNumber"));

		HttpSession session = req.getSession(false);

		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;
		int mNumber = user.getmNumber();

		String startDateVal = req.getParameter("startDate");
		Date startDate = Date.valueOf(startDateVal);

		String periodVal = req.getParameter("period");
		int period = Integer.parseInt(periodVal);

		int sNumber = volunteerService.findsNumber(abNumber);

		VolunteerApplySubmitForm volunteerApplySubmitForm = new VolunteerApplySubmitForm(mNumber, startDate, period,
				sNumber);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			volunteerService.volunteer(volunteerApplySubmitForm);
			req.setAttribute("abNumber", abNumber);

			return "/WEB-INF/view/abandoned_animal/submitSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);

			return FORM_VIEW;
		}
	}
}
