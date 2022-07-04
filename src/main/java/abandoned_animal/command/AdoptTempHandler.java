package abandoned_animal.command;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abandoned_animal.form.AdoptTempSubmitForm;
import abandoned_animal.service.AdoptTempService;
import auth.service.User;
import mvc.command.CommandHandler;

public class AdoptTempHandler implements CommandHandler {

	private AdoptTempService adoptTempService = new AdoptTempService();
	private static final String FORM_VIEW = "/WEB-INF/view/abandoned_animal/adoptTempSubmitForm.jsp";

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
		HttpSession session = req.getSession(false);
		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;

		int mNumber = user.getmNumber();
		int abNumber = Integer.parseInt(req.getParameter("abNumber"));
		
		String memberName = adoptTempService.findMemberName(mNumber);
		String animalName = adoptTempService.findAnimalName(abNumber);

		if (animalName != null) {
			req.setAttribute("animalName", animalName);
		}
		
		if (memberName != null) {
			req.setAttribute("memberName", memberName);
		}
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		Object userObj = session.getAttribute("authUser");
		User user = (User) userObj;

		int mNumber = user.getmNumber();
//		int abNumber = Integer.parseInt(req.getParameter("abNumber"));
//      String abNumberVal = req.getParameter("abNumber");
//      int abNumber = 1;
//
//      if (abNumberVal != null) {
//          abNumber = Integer.parseInt(abNumberVal);
//      }
		
		String abNumberVal = req.getParameter("abNumber");
		int abNumber = 1;
		if(abNumberVal != null) {
			abNumber = Integer.parseInt(abNumberVal);
		}
		
		String adoptOrTemp = req.getParameter("adoptOrTemp");
		// adoptOrTemp = req.getParameter("adoptOrTemp");
		String residence = req.getParameter("residence");
		String maritalStatus = req.getParameter("maritalStatus");
		String job = req.getParameter("job");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		AdoptTempSubmitForm adoptTempSubmitForm = new AdoptTempSubmitForm(mNumber, abNumber, residence, maritalStatus,
				job);

		try {
			if (adoptOrTemp.equals("adopt")) {
				adoptTempService.adopt(adoptTempSubmitForm);
			}

			if (adoptOrTemp.equals("temp")) {
				adoptTempService.tempProtect(adoptTempSubmitForm);
			}

			req.setAttribute("abNumber", abNumber);

			return "/WEB-INF/view/abandoned_animal/submitSuccess.jsp";
		} finally {

		}
	}

//   @Override
//   public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
//       String abNumberVal = req.getParameter("abNumber");
//       int abNumber = 1;
// 
//       if (abNumberVal != null) {
//           abNumber = Integer.parseInt(abNumberVal);
//       }
// 
//       AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
//       req.setAttribute("detailForm", detailForm);
// 
//       return "/WEB-INF/view/abandoned_animal/adoptAndTemp.jsp";
//   }

//        LoginService loginService = new LoginService();
//        String id = trim(req.getParameter("id"));
//        String password = trim(req.getParameter("pw"));
//        User authUser = (User) req.getSession().getAttribute("authUser");
//        
//        if (authUser != null) {
//        	return FORM_VIEW;
//        } else {
//        	res.sendRedirect(req.getContextPath() + "/login.do");
//        	return null;
//        }
//  
//	private String trim(String str) {
//		return str == null ? null : str.trim();
//	}

}
