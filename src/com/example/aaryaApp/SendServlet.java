package com.example.aaryaApp;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@SuppressWarnings("serial")
public class SendServlet extends HttpServlet {
     
    private static final Logger logger = Logger.getLogger(SendServlet.class.getCanonicalName());
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	logger.warning("A Sample Warning!");
    	String cloudId="";
    	String localId=req.getParameter(Constants.LOCAL_ID);
    	String msg = req.getParameter(Constants.MSG);
    	String date=req.getParameter(Constants.DATE);
    	String dueDate=req.getParameter(Constants.DUEDATE);
        String from = req.getParameter(Constants.FROM);
        String to = req.getParameter(Constants.TO);
        String body="Hi,<br/>" +
        		"A new reminder was created by: "+from+
        		" for you."+
        		"<br/>Task Title: "+msg+
        		"<br/>Due date: "+dueDate+
        		"<br/>Kindly Log in with our app." +
        		"<p>Thanks, <br/>AryaApp Team";
        // Converting String of emailIDs to List
        List<String> toList=CommonUtilities.StringToList(to);
        
        logger.warning(msg+": "+from+": "+to);
        Contact contact = null;
        Contact fromContact=null;
        EntityManager em = EMFService.get().createEntityManager();
        Tasks newTask=null;
        try {
        	Date taskCreatedDate= new Date();     
        	Long time=taskCreatedDate.getTime();
        	newTask= new Tasks(localId,from,to, msg,date, dueDate, time);        	
        	em.persist(newTask);	
        	em.close();
        	em = EMFService.get().createEntityManager();        	
        	newTask= Tasks.find(time, em);			
        	try {
        		
                cloudId= String.valueOf(newTask.getId());
			} catch (Exception e) {
				logger.warning("Last Option..:| :"+e.getMessage());
			}
        	
		} catch (Exception e) {
			logger.warning("Error: "+e.getMessage());
		}
        logger.warning("Check Point em ...:P");
        String fromRegId="";
        try {
        	fromContact=Contact.find(from, em);
            fromRegId=fromContact.getRegId();
		} catch (Exception e) {
			logger.warning("Contac issue: "+e.getMessage());
		}
        
        Message message = new Message.Builder()
//      .delayWhileIdle(true)
        .addData(Constants.TO, to).addData(Constants.FROM, from).addData(Constants.MSG, msg)
        .addData(Constants.DATE, date).addData(Constants.LOCAL_ID, localId)
        .addData(Constants.DUEDATE, dueDate).addData(Constants.CLOUD_ID, cloudId)
        .build();
        Sender sender = new Sender(Constants.API_KEY);
        Result resultFrom=sender.send(message, fromRegId, 5);
        logger.log(Level.WARNING, "Result: " + resultFrom.toString());
        
        try {
        	for (String toEmail : toList) {
        		try {
        			logger.warning("toEmail: "+toEmail);
                    contact = Contact.find(toEmail, em);           
                    if (contact == null) 
                    	{
                    		logger.warning("Sending email...");
                    		new EmailService().SendEmail(EmailService.from, toEmail, "AryaApp: New Reminder", body);                   		
                    		continue;
                    	}
                } catch (Exception e) {
                	logger.warning("Mail Exception...");
    			}
                 
                String regId = contact.getRegId();             
                logger.warning("So far so good. RegID: "+regId); 
                try {
                    Result result = sender.send(message, regId, 5);
//                    result=sender.send(message, fromRegId, 5);
        /*          List<String> regIds = new ArrayList<String>();
                    regIds.add(regId);
                    MulticastResult result = sender.send(message, regIds, 5);*/
                    logger.log(Level.WARNING, "Result: " + result.toString());                
                } catch (IOException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                    logger.info("Exception Occured..:(");
                }
    		}   
		} catch (Exception e) {
			logger.warning("Loop Issue...");
		}finally{
			em.close();
		}
    	    
    }
}
	