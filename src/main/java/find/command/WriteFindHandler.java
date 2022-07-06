package find.command;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import auth.service.User;

import find.form.FindWriteForm;
import find.service.FindService;

import mvc.command.CommandHandler;


public class WriteFindHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/picBoard/findForm_WRITE.jsp";

    private FindService findService = new FindService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return FORM_VIEW;
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

            return null;
        }
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        Map<String, Boolean> errors = new HashMap<>();
        User user = (User) req.getSession(false).getAttribute("authUser");

        req.setAttribute("errors", errors);

        String realPath = req.getRealPath("upload");
        System.out.println("path=" + realPath);
        int maxSize = 10 * 1024 * 1024; // 10MB, 최대 2GB
        FindWriteForm writeReq = null;

        try {
            MultipartRequest multi = new MultipartRequest(req, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
            if(multi.getFilesystemName("imgPath") == null) {
                writeReq = createWriteRequestNoImg(user, multi);
            } else {
                writeReq = createWriteRequest(user, multi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (writeReq != null) {
            writeReq.validate(errors);
        }

        /*if (!errors.isEmpty()) {
            return FORM_VIEW;
        }*/

        try {
            findService.write(writeReq);

            return "/WEB-INF/view/picBoard/findForm_SUCCESS.jsp";
        } catch (Exception e) {
            e.printStackTrace();

            return FORM_VIEW;
        }
    }

    private FindWriteForm createWriteRequest(User user, MultipartRequest multi) {
        return new FindWriteForm(user.getmNumber(),
                multi.getParameter("species"),
                multi.getParameter("kind"),
                multi.getParameter("location"),
                multi.getFilesystemName("imgPath"),
                multi.getParameter("title"),
                multi.getParameter("content"));
    }

    private FindWriteForm createWriteRequestNoImg(User user, MultipartRequest multi) {
        return new FindWriteForm(user.getmNumber(),
                multi.getParameter("species"),
                multi.getParameter("kind"),
                multi.getParameter("location"),
                "no_image.png",
                multi.getParameter("title"),
                multi.getParameter("content"));
    }
}