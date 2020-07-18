package csvJson;

public class Bundle
{
	String bundleID;
	String factoryID;

	public Bundle(String row[])
	{
		this.bundleID = row[0];
		this.factoryID = row[1];
	}
}