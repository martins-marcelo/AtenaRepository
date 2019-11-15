package com.marcelomartins.atena.controllers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.marcelomartins.atena.controllers.converters.JSONFlattener;

public class BuscaController {
	private Integer pullNumber; //Number of a specific pull request
	private Integer numberOfPulls; //Number of commits associated to one specific pull request
	
	public void buscarRepositorio(String rep) throws Exception{
		/*
		 * Criando JSON a partir de URL
		 */
		List<Map<String, String>> flatJson = JSONFlattener.parseJson(
			new URI(
				"https://api.github.com/repos/"+rep+"/pulls?state=closed"
			)
		);
		  
	}
	
}
