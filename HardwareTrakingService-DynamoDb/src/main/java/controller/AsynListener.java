package controller;
import controller.MyQueueListener;

import credentials.Cred;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;

@Component
public class AsynListener 
{
	String queueName ;
	
	public AsynListener()
	{
		queueName = "s3Queue";
	}
	
	public void runAsync()throws JMSException , InterruptedException
	{
		Cred crd = new Cred();
		SQSConnectionFactory conFac = new SQSConnectionFactory(new ProviderConfiguration(),crd.awsSqs());
		SQSConnection con = conFac.createConnection();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(queueName);
		MessageConsumer cons = session.createConsumer(queue);
		cons.setMessageListener(new MyQueueListener());
		con.start();
		Thread.sleep(1000);
	}
}
