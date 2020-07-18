package csvJson;
import csvJson.Date;

public class Product 
{
	String productID,bundleID;
	Date manfDate , shipDate , instDate;
	Double version,weight;
	
	public Product(String row[])
	{
		manfDate = new Date();
		shipDate = new Date();
		instDate = new Date();
		
		this.productID = row[0];
		this.bundleID = row[1];
		this.manfDate.day = Integer.parseInt(row[2]);
		this.manfDate.month = Integer.parseInt(row[3]);
		this.manfDate.year = Integer.parseInt(row[4]);
		
		this.shipDate.day = Integer.parseInt(row[5]);
		this.shipDate.month = Integer.parseInt(row[6]);
		this.shipDate.year = Integer.parseInt(row[7]);

		this.instDate.day = Integer.parseInt(row[8]);
		this.instDate.month = Integer.parseInt(row[9]);
		this.instDate.year = Integer.parseInt(row[10]);
		
		this.version = Double.parseDouble(row[11]);
		this.weight = Double.parseDouble(row[12]);
	}
}
