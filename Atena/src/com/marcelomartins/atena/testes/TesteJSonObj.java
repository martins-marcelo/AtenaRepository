package com.marcelomartins.atena.testes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.*;

import com.marcelomartins.atena.controllers.BuscaController;



public class TesteJSonObj{

	public static void main(String[] args) throws IOException {
		
		BuscaController bc = new BuscaController();
		
		
		/* Codigo sendo executado na classe BuscaController
		
		URL url = new URL("https://api.github.com/repos/rails/rails/pulls?state=closed");
		System.out.println("begin");
		System.out.println("URL: "+url);

		//ArrayList para capturar numeros dos pull requests
		List<JsonObject> lstPulls = new ArrayList<JsonObject>();


		InputStream ins = url.openStream();
		JsonReader rdr = Json.createReader(ins);
		JsonStructure stru = rdr.read();
		JsonArray jarr = stru.asJsonArray();
		for(int i = 0; i < jarr.size(); i++) {
			lstPulls.add(jarr.getJsonObject(i));
		}

		for(int i = 0; i < jarr.size(); i++) {
			System.out.println("Numero: "+i);
			System.out.println(lstPulls.get(i).getInt("number"));
		}
		 */
	}

}
