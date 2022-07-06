package abandoned_animal.command;

import abandoned_animal.form.AbandonedAnimalDetailForm;
import abandoned_animal.form.AbandonedAnimalPage;
import abandoned_animal.service.AbandonedAnimalService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAbandonedAnimalHandler implements CommandHandler {

    private AbandonedAnimalService abandonedAnimalService = new AbandonedAnimalService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String abNumberVal = req.getParameter("abNumber");
        int abNumber = 1;

        if (abNumberVal != null) {
            abNumber = Integer.parseInt(abNumberVal);
        }

        AbandonedAnimalDetailForm detailForm = abandonedAnimalService.getDetailForm(abNumber);
        req.setAttribute("detailForm", detailForm);

        return "/WEB-INF/view/abandoned_animal/detailAbandonedAnimal.jsp";
    }
}
