package com.example.aaryaApp;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UnregisterServlet extends HttpServlet {
     
    //private static final Logger logger = Logger.getLogger(UnregisterServlet.class.getCanonicalName());
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(Constants.FROM);
         
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Contact contact = Contact.find(email, em);
            if (contact != null) {
                em.remove(contact);
                //logger.log(Level.WARNING, "Unregistered: " + contact.getId());
            }
        } finally {
            em.close();
        }      
    }
}
  
