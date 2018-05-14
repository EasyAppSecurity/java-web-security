package net.easyappsecurity.terracotta.defense.http;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

public interface CsrfDetectionFilter extends Filter {
	boolean hasCsrf(HttpServletRequest request);
}
