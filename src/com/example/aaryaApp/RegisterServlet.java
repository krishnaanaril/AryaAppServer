package com.example.aaryaApp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(RegisterServlet.class.getCanonicalName());
	 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
        String email = req.getParameter(Constants.FROM);
        String regId = req.getParameter(Constants.REG_ID);
        String userName = req.getParameter(Constants.USER_NAME);
        String userPassword = req.getParameter(Constants.USER_PASSWORD);
        logger.warning("Got here...");
        logger.warning("Email: "+email);
        logger.warning("Regid: "+regId);
        logger.warning("User: "+userName);
        logger.warning("Password: "+userPassword);
        
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Contact contact = Contact.find(email, em);
            if (contact == null) {
            	logger.warning("Contact created 1...");
                contact = new Contact(email, regId, userName, userPassword);
            } else {
            	logger.warning("Contact Created 2..."+email);
                contact.setRegId(regId);
            }
            em.persist(contact);
            logger.warning(" Em Registered: " + contact.getId());
            logger.log(Level.WARNING, "Registered: " + contact.getId());
        } 
        catch (Exception e) {
			logger.info("Error"+e.getMessage());
		}
        finally {
            em.close();
        }
    }
}
