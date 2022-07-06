package find.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import find.form.FindInForm;
import find.service.ListFindService;

import mvc.command.CommandHandler;


public class InFindHandler implements CommandHandler {

    private ListFindService listFindService = new ListFindService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        String faNumberVal = req.getParameter("faNumber");
        int faNumber = 1;

        if (faNumberVal != null) {
            faNumber = Integer.parseInt(faNumberVal);
        }

        FindInForm findInForm = listFindService.getInForm(faNumber);
        req.setAttribute("findIn", findInForm);

        return "/WEB-INF/view/picBoard/findForm_IN.jsp";
    }
}