package Automation.SeleniumFrameWork.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//read Json to string
		String jsonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//Automation//SeleniumFrameWork//Data//PurchaseOrder.json"),StandardCharsets.UTF_8);
	
	     // Now convert String content into Hashmap - Jackson Databind ( we need to add this dependency in pom.xml)
	    
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
        // this data returned will be list of hashMaps {map, map}
	
	}
	 // instead of having datareader class , we are adding this method in BaseTest so we can call it to our program directly without creating objects.
	
	}


