package com.example.aaryaApp;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AaryaAppServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(SendServlet.class.getCanonicalName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		logger.warning("A Sample Warning!");
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
