package com.marcelomartins.atena.testes;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.*;


import weka.core.converters.JSONLoader;

public class TesteJSonObj{

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://api.github.com/repos/spring-by-example/spring-by-example");

//		InputStream is = url.openStream();
//		JsonReader reader = Json.createReader(is);	
//		
//		JsonObject obj = reader.readObject();
//		JsonArray results = obj.getJsonArray("data");
//		
//		for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//			System.out.println(result.getJsonObject("from").getString("name"));
//			System.out.println(": ");
//			
//		}
		try{
			InputStream ins = url.openStream();
			JsonReader rdr = Json.createReader(ins);
			JsonObject obj = rdr.readObject();
			JsonArray results = obj.getJsonArray("data");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				System.out.println(result.getJsonObject("from").getString("name"));
				System.out.println(": ");
				System.out.println();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


	}



}
