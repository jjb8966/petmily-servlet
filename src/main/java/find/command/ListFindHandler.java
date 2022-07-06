package find.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import find.form.FindPageForm;
import find.service.ListFindService;

import mvc.command.CommandHandler;

public class ListFindHandler implements CommandHandler {

	private ListFindService listFindService = new ListFindService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;

		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		FindPageForm Finds = listFindService.getFindPage(pageNo);
		req.setAttribute("Finds", Finds);

		return "/WEB-INF/view/picBoard/findForm.jsp";
	}
}