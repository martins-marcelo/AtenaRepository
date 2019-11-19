package com.marcelomartins.atena.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import com.marcelomartins.atena.domain.Repositorio;

public class BuscaController {
	
	
	//ArrayList para capturar os dados dos pull requests de acordo com o array lstPulls
	private List<JsonObject> lstJsPulls = new ArrayList<JsonObject>();
	private JsonArray jarr;

	/*
	 * Realiza a busca de todos os pull requests de um repositorio
	 * Armazena o numero para busca de seus endpoints
	 * Deve ser ligado ao botão da view
	 */
	public List<JsonObject> buscarPulls(String rep) throws IOException{
		int iter = 1;
		//ArrayList para capturar numeros dos pull requests
		List<Integer> lstPulls = new ArrayList<Integer>();
		//ArrayList of Repositorio objects that capture from Json,
		//only the attributes that has been specificated
		List<Repositorio> lstReps = new ArrayList<Repositorio>();
		
		/* Fase 1: Recuperar os endpoints para as paginas dos pulls*/
		do {
			URL url = new URL(
					"https://api.github.com/repos/"+rep+"/pulls?state=closed&page="+iter);
			System.out.println("URL begin at: "+url);
			InputStream ins = url.openStream();
			JsonReader rdr = Json.createReader(ins);
			JsonStructure stru = rdr.read();
			jarr = stru.asJsonArray();
			for(int j = 0; j < jarr.size(); j++) {
				lstPulls.add(jarr.getJsonObject(j).getInt("number"));
			}
			System.out.println("Stage "+iter+" succesful!");
			iter++;
		}while(jarr.size() > 0);
		/* Fim da fase 1*/
		
		/* Fase 2: Gravar em objetos Java os atributos desejados do JsonObject*/
		for(int j = 0; j < lstPulls.size(); j++) {
			Repositorio r = new Repositorio();
			URL url = new URL(
					"https://api.github.com/repos/"+rep+"/pulls/"+lstPulls.get(j));
			System.out.println("URL begin at: "+url);
			InputStream ins = url.openStream();
			JsonReader rdr = Json.createReader(ins);
			JsonObject obj = rdr.readObject();
			//Adicionar metodos set aqui, faltou tempo
			lstReps.add(r);
		}
		return lstJsPulls;		
	}
	

}
