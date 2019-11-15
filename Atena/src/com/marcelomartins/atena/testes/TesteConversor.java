package com.marcelomartins.atena.testes;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.marcelomartins.atena.controllers.converters.CSVWriter;
import com.marcelomartins.atena.controllers.converters.JSONFlattener;


public class TesteConversor {
	/*
	 * Convertendo arquivo JSON para CSV
	 */
	public static void main(String[] args) throws Exception{
		
		/*
		 * Convertendo JSON a partir de URL
		 */
		List<Map<String, String>> flatJson = JSONFlattener.parseJson(
			new URI(
				"https://api.github.com/repos/rails/rails/pulls?state=closed&per_page=200"
			)
		);
		
//		CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, "\t"), "files/rails_closed.csv");
//		String[] lstAtr = flatJson.toString().split(",");
//		for (String string : lstAtr) {
//			System.out.println(string);
//		}
		
		System.out.println(flatJson.size());
	}

}
