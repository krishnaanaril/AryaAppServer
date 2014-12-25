package com.example.aaryaApp;

public interface EmailTemplates {
	
	String subscriptionMail="Hi,<br/>" +
    		"<p>Thanks for showing interest in Havents app. We will keep you updated." +
    		"<p>Thanks, <br/>Havents Team";
	
	String newSubscriberNotification="Hi,<br/>" +
    		"<p>New Subscriber." +
			"<p>User: @username"+
    		"<p>Thanks, <br/>Havents Team";
}
