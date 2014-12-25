package com.example.aaryaApp;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.prospectivesearch.Subscription;

@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(RegisterServlet.class.getCanonicalName());
	 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	try {
    		String userName = req.getParameter(Constants.USER_NAME);
        	String email = req.getParameter(Constants.FROM);
            String body="Hi,<br/>" +
            		"<p>Thanks for showing interest in Havents app. We will keep you updated." +
            		"<p>Thanks, <br/>AryaApp Team";
        	logger.warning("Email: "+email);
            logger.warning("User: "+userName);	            
            
            EntityManager em = EMFService.get().createEntityManager();
            try {
            	com.example.aaryaApp.Subscription subscription= com.example.aaryaApp.Subscription.find(email, em);
            	if (subscription == null) {
                	logger.warning("New Subscriber...:)");
                	subscription = new com.example.aaryaApp.Subscription(userName,email, false);
                	new EmailService().SendEmail(EmailService.from, email, "Havents: New Reminder", EmailTemplates.subscriptionMail);
                	new EmailService().SendEmail(EmailService.from, Constants.ADMINMAIL, "Havents: New Subscriber", EmailTemplates.newSubscriberNotification.replace("@username", userName));
                } else {                	 
                	logger.warning("Duplicate Entry");
                }
                em.persist(subscription);
                logger.warning(" Em Registered: " + subscription.getId());
            	
			} catch (Exception e) {
				logger.info("Error"+e.getMessage());
			}
	        finally {
	            em.close();
	        }
            
		} catch (Exception e) {
			logger.info("Error"+e.getMessage());
		}
        finally {
            
        }	    	
    }

}
