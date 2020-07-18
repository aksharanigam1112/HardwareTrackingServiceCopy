package controller;

import s3.Bucket;
import org.json.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyQueueListener implements MessageListener
{
	Logger logger = LoggerFactory.getLogger(MyQueueListener.class);
	private Bucket bkt;
	public MyQueueListener()
	{
		// TODO calling
	}
	
	@Autowired
	public MyQueueListener(Bucket bkt) 
	{
		this.bkt = bkt;
	}

	public void onMessage(Message message)
	{
		try
		{
			String msg = ((TextMessage)message).getText();
			
//			Bucket bkt = new Bucket();
			
			String filename = getFileName(msg);
			bkt.readFromFile(filename);	
		}
		catch(JMSException e)
		{
			logger.error("Eroor Occured :-\n"+e);
		}
	}
	
	public String getFileName(String str)
	{
		JSONObject json = new JSONObject(str);
        JSONArray records = json.getJSONArray("Records");
        JSONObject r = new JSONObject(records.get(0).toString());
        JSONObject object = new JSONObject(r.get("s3").toString());                
        JSONObject details = new JSONObject(object.get("object").toString());
        
        String filename = details.get("key").toString();
        
        logger.info("File name :- "+filename);
        
        return filename;
	}
}
