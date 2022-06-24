package abandoned_animal.command;

import abandoned_animal.model.AbandonedAnimal;
import abandoned_animal.service.AbandonedAnimalPage;
import abandoned_animal.service.ListAbandonedAnimalService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListAbandonedAnimalHandler implements CommandHandler {

    private ListAbandonedAnimalService listAbandonedAnimalService = new ListAbandonedAnimalService();

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
