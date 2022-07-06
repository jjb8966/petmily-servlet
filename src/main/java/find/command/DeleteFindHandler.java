package find.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;

import find.service.FindService;

import mvc.command.CommandHandler;

public class DeleteFindHandler implements CommandHandler {

    private FindService deleteService = new FindService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        User authUser = (User) req.getSession().getAttribute("authUser");
        String faNumberVal = req.getParameter("faNumber");
        int faNumber = Integer.parseInt(faNumberVal);

        try {
            deleteService.delete(faNumber, authUser);

            return "/WEB-INF/view/picBoard/findForm_SUCCESS.jsp";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
        /*catch (FindNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);

            return null;
        } catch (PermissionDeniedException e) {
        	res.sendError(HttpServletResponse.SC_FORBIDDEN);
        	return null;
        }*/
    }
}