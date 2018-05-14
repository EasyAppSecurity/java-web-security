package net.easyappsecurity.terracotta.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.easyappsecurity.terracotta.defense.fs.ImageDetector;
import net.easyappsecurity.terracotta.defense.fs.TikaBasedImageDetector;
import net.easyappsecurity.terracotta.defense.fs.VirusCheckingImageDetector;
import net.easyappsecurity.terracotta.defense.http.CookieBasedCsrfTokenRepository;
import net.easyappsecurity.terracotta.defense.http.CsrfTokenRepository;
import net.easyappsecurity.terracotta.service.*;

/**
 * Application Lifecycle Listener implementation class ApplicationContextConfigurer
 *
 */
@WebListener
public class ApplicationContextConfigurer implements ServletContextListener {
	private final ApplicationContext context = new ApplicationContext();
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	context.clear();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	context.set(new AccountService());
    	context.set(new UserService());
    	context.set(new CheckService());
    	context.set(new EmailService());
    	context.set(new MessageService());
    	context.set(new RedirectCache());
    	context.put(ImageDetector.class, new VirusCheckingImageDetector(new TikaBasedImageDetector()));
    	context.put(CsrfTokenRepository.class, new CookieBasedCsrfTokenRepository());
    	arg0.getServletContext().setAttribute("applicationContext", context);
    	
    }
	
}
