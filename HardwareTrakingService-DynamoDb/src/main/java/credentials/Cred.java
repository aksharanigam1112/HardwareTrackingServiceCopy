package credentials;

import org.springframework.stereotype.Component;

import com.HardwareTrackingService.controller.ProductController;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Component
public class Cred 
{
	ProfileCredentialsProvider credentialsProvider; 
	
	public Cred()
	{
		credentialsProvider= new ProfileCredentialsProvider();
	}	
	
	public AmazonS3 awsS3()
	{
        AWSCredentials credentials = credentialsProvider.getCredentials();

        try 
        {
        	AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion("us-west-2")
                    .build();  
        	return s3;
        } 
        catch (Exception e) 
        {
        	throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    e);
        }		
	}
	
	public AmazonSQS awsSqs()
	{
		try 
		{
			AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_WEST_2)
                .build();
			return sqs;
		}
		catch(Exception e)
		{
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    e);
		}
	}
}
