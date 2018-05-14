package net.easyappsecurity.terracotta.servlet;

import net.easyappsecurity.terracotta.app.ApplicationAwareServlet;
import net.easyappsecurity.terracotta.model.Message;
import net.easyappsecurity.terracotta.service.MessageService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactUsServlet
 */
@WebServlet("/contactus")
public class ContactUsServlet extends ApplicationAwareServlet {
    private static final long serialVersionUID = 1L;

    private Long nextMessageId = 2L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("contactName");
        String email = request.getParameter("contactEmail");
        String subject = request.getParameter("contactSubject");
        String message = request.getParameter("contactMessage");
        Message m = new Message(String.valueOf(nextMessageId++), name, email, subject, message);
        context.get(MessageService.class).addMessage(m);
    }

}
