package net.easyappsecurity.terracotta.servlet;

import net.easyappsecurity.terracotta.app.ApplicationAwareServlet;
import net.easyappsecurity.terracotta.app.RedirectCache;
import net.easyappsecurity.terracotta.model.Message;
import net.easyappsecurity.terracotta.service.MessageService;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MessagesServlet
 */
@WebServlet("/showMessages")
public class MessagesServlet extends ApplicationAwareServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authenticatedUser") == null) {
			/*//
			String relay = request.getRequestURL().toString();
			//*/

            //*/
            RedirectCache cache = context.get(RedirectCache.class);
            String relay = cache.key(request.getRequestURL().toString());
            //*/

            response.sendRedirect(request.getContextPath() + "/employee.jsp?relay=" + relay);
        } else {
            Set<Message> messages = context.get(MessageService.class).findAll();
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
        }
    }
}
