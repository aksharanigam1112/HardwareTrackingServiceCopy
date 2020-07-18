package s3;

import credentials.Cred;
import s3.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Bucket 
{
	Logger logger = LoggerFactory.getLogger(Bucket.class);
	static String bucketName =  "factorybundle";
	AmazonS3 s3;
	private Handler hand;
	
	public Bucket()
	{
		Cred cred = new Cred();
		s3 = cred.awsS3();
	}
	
	@Autowired
	public Bucket(Handler hand)
	{
		this.hand = hand;
	}
	
	public void readFromFile(String fileName)
	{		
//		Handler hand = new Handler();
		
		S3Object s3object = s3.getObject(new GetObjectRequest(bucketName, fileName));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));				
				
		String line ;
		
		List<String[]> alldata = new ArrayList<String[]>();
		
		try
		{
			line = reader.readLine();
			int size = line.split(",").length;
			
			while((line = reader.readLine()) != null) 
			{
				String Data[] = new String[size];
				for(int i=0;i<size;i++)
				{
					Data[i] = line.split(",")[i];
				}
				alldata.add(Data);
			}
			
			if(size==2)
				hand.saveBundle(alldata);			
			
			else
				hand.saveProduct(alldata);
			
		}
		catch(IOException e)
		{
			logger.error("Error has occured :-\n"+e);
		}
	}
}