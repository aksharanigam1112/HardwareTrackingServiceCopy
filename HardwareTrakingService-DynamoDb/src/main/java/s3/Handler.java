package s3;

//import csvJson.Bundle;
//import csvJson.Product;

import com.HardwareTrackingService.model.Bundle;
import com.HardwareTrackingService.model.Product;
import com.HardwareTrackingService.Services.ProductService;
import com.HardwareTrackingService.Services.BundleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Handler 
{   
	Logger logger = LoggerFactory.getLogger(Handler.class);

	private BundleService bundle;
	private ProductService prd;
	private Bundle bobj;
	private Product pobj;
	
	@Autowired
	Handler(BundleService bundle, ProductService prd, Bundle bobj, Product pobj)
	{
		this.bundle = bundle;
		this.prd = prd;
		this.bobj = bobj;
		this.pobj = pobj;
	}

	public void saveBundle(List<String[]> allData)
    {
    	try 
    	{ 
//            List<Bundle> list = new ArrayList<Bundle>();
            
            for (String[] row : allData) 
            { 
            	bobj.setBundleId(row[0]);
            	bobj.setFactoryId(row[1]);
            	bundle.createBundle(bobj);
//            	list.add(bobj);
            }
            
//            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
//    		String prettyJson = prettyGson.toJson(list);
//    		System.out.println("\nJSONObject of Bundle==> " + prettyJson);
        } 
        catch (Exception e) 
    	{ 
            logger.error("Error has occurred :-\n"+e);
        } 	
    }
	
	
    public void saveProduct(List<String[]> allData)
    {
    	try 
    	{ 
//            List<Product> list = new ArrayList<Product>();
            
            for (String[] row : allData) 
            { 

            	pobj.setProductId(row[0]);
            	pobj.setBundleId(row[1]);
            	pobj.setManufactureDate(row[2]);
            	pobj.setShipmentDate(row[3]);
            	pobj.setInstallationDate(row[4]);
            	pobj.setFirmware_version(row[5]);
            	pobj.setWeight(row[6]);
            	prd.createProduct(pobj);
//            	list.add(pobj);
            }
            
//            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
//    		String prettyJson = prettyGson.toJson(list);
//    		System.out.println("\nJSONObject of Product ==> " + prettyJson);
        } 
        catch (Exception e) 
    	{ 
        	logger.error("Error has occured :-\n"+e);
        } 	
    }
}