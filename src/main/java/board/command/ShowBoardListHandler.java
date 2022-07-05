package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardPage;
import board.service.ListBoardService;
import mvc.command.CommandHandler;

public class ShowBoardListHandler implements CommandHandler {
	
    private ListBoardService listService = new ListBoardService();
    
    	@Override
    	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    		String pbNumberVal = req.getParameter("pbNumber");
    		String kindOfBoard = req.getParameter("kindOfBoard");
    		int pbNumber = 1;
    		
    		if (pbNumberVal != null) {
    			pbNumber = Integer.parseInt(pbNumberVal);
    		}
    		
    		BoardPage boardPage = listService.getBoardPage(pbNumber, kindOfBoard);
    		req.setAttribute("boardPage", boardPage);
    		req.setAttribute("kindOfBoard", kindOfBoard);
    
     		return "/WEB-INF/view/board/boardList.jsp";
    	}
}