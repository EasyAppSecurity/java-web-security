package net.easyappsecurity.terracotta.testng;

import net.easyappsecurity.terracotta.TomcatBootstrapper;
import org.apache.catalina.startup.Tomcat;


public class TomcatSupport {
	private static Tomcat tomcat;

	public void startContainer() throws Exception {
		tomcat = new TomcatBootstrapper().startTomcat(8080, "src/main/webapp");
	}
	
	public void stopContainer() throws Exception {
		tomcat.stop();
	}
}
