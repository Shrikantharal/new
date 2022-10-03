package parent.data;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DataReader {

	public void getJasonToMap() throws IOException
	{
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//parent//data//purchaseOrder.json"));
		//String to Hashmap Jackson databind(getit from MVN)
	
	}
}
