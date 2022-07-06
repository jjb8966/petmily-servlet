package find.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import find.form.FindWriteForm;
import find.service.ListFindService;
import find.service.FindService;

import mvc.command.CommandHandler;

public class ModifyFindHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/picBoard/findForm_MODIFY.jsp";

	private ListFindService readService = new ListFindService();
	private FindService modifyService = new FindService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
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
		try {
			String faNumberVal = req.getParameter("faNumber");
			int faNumber = Integer.parseInt(faNumberVal);
			
			FindWriteForm findData = readService.getFind(faNumber, false);
			User authUser = (User) req.getSession().getAttribute("authUser");

			if (!canModify(authUser, findData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			FindWriteForm findMod = new FindWriteForm(faNumber, authUser.getmNumber(), findData.getSpecies(),
					findData.getKind(), findData.getLocation(), findData.getImgPath(), findData.getTitle(),
					findData.getContent());

			req.setAttribute("findMod", findMod);
			
			return FORM_VIEW;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		/*catch (FindNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		}*/
	}

	private boolean canModify(User authUser, FindWriteForm findData) {
		return authUser.getmNumber() == findData.getmNumber();
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		User authUser = (User) req.getSession().getAttribute("authUser");
		
		String faNumberVal = req.getParameter("faNumber");
		int faNumber = Integer.parseInt(faNumberVal);

		req.setAttribute("errors", errors);

		String realPath = req.getRealPath("upload");
		System.out.println("path=" + realPath);
		int maxSize = 10 * 1024 * 1024; // 10MB, 최대 2GB
		FindWriteForm findMod = null;

		try {
			MultipartRequest multi = new MultipartRequest(req, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			if(multi.getFilesystemName("imgPath") == null) {
				findMod = createModifyRequestNoImg(faNumber, authUser, multi);
			} else {
				findMod = createModifyRequest(faNumber, authUser, multi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("findMod", findMod);

		if (findMod != null) {
			findMod.validate(errors);
		}

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			modifyService.modify(findMod);
			
			return "/WEB-INF/view/picBoard/findForm_SUCCESS.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			
			return FORM_VIEW;
		}
		/*
		 * catch (FindNotFoundException e) {
		 * res.sendError(HttpServletResponse.SC_NOT_FOUND); return null; } catch
		 * (PermissionDeniedException e) {
		 * res.sendError(HttpServletResponse.SC_FORBIDDEN); return null; }
		 */
	}

	private FindWriteForm createModifyRequest(int faNumber, User user, MultipartRequest multi) {
		return new FindWriteForm(faNumber,
				user.getmNumber(),
				multi.getParameter("species"),
				multi.getParameter("kind"),
				multi.getParameter("location"),
				multi.getFilesystemName("imgPath"),
				multi.getParameter("title"),
				multi.getParameter("content"));
	}

	private FindWriteForm createModifyRequestNoImg(int faNumber, User user, MultipartRequest multi) {
		return new FindWriteForm(faNumber,
				user.getmNumber(),
				multi.getParameter("species"),
				multi.getParameter("kind"),
				multi.getParameter("location"),
				"no_image.png",
				multi.getParameter("title"),
				multi.getParameter("content"));
	}
}