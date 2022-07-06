package abandoned_animal.command;

import abandoned_animal.form.AbandonedAnimalPage;
import abandoned_animal.service.AbandonedAnimalService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAbandonedAnimalHandler implements CommandHandler {

    private AbandonedAnimalService listAbandonedAnimalService = new AbandonedAnimalService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String pageNoVal = req.getParameter("pageNo");
        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        AbandonedAnimalPage abandonedAnimals = listAbandonedAnimalService.getAbandonedAnimalPage(pageNo);
        req.setAttribute("abandonedAnimals", abandonedAnimals);

        return "/WEB-INF/view/abandoned_animal/listAbandonedAnimal.jsp";
    }
}
