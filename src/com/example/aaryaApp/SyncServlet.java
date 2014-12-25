package com.example.aaryaApp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SyncServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(SendServlet.class.getCanonicalName());
	 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    
    	try {
//			int length=req.getContentLength();	
//			byte[] input= new byte[length];
//			ServletInputStream sin = req.getInputStream();
//            int c, count = 0 ;
//            while ((c = sin.read(input, count, input.length-count)) != -1) {
//                count +=c;
//            }
//            sin.close();
// 
//            String recievedString = new String(input);
    		String recievedString = req.getParameter("id");
    		logger.warning(recievedString);
            resp.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
 
            Integer doubledValue = Integer.parseInt(recievedString) * 2;
 
            writer.write("{\"key1\": \"value1\", \"key2\": \"value2\"}");
            writer.flush();
            writer.close();
		} catch (Exception e) {
			logger.warning("Oops: "+e.getMessage());
			try { 
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().print(e.getMessage());
				resp.getWriter().close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}        
    }
}
