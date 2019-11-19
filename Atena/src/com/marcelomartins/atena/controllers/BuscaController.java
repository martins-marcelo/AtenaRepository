package com.marcelomartins.atena.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class BuscaController {
	//ArrayList para capturar numeros dos pull requests
	private List<Integer> lstPulls = new ArrayList<Integer>();
	private JsonArray jarr;
	
	/*
	 * Realiza a busca de todos os pull requests de um repositorio
	 * Armazena o numero para busca de seus endpoints
	 * Deve ser ligado ao botão da view
	 */
	public void buscarTodosPullsRepositorio(String rep) throws IOException{
		/* Carater experimental: Fase de testes*/
		do {
			int iter = 1;
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
		}while(jarr.size() > 0);
	}
	
	
	/*
	 * Retorna a quantidade de pull requests com state=closed associados a um projeto
	 * Retorna 0 caso a busca de repositórios ainda nao tiver sido feita
	 */
	public Integer qtdRepositorios() {
		return jarr.size();
	}
	
	/*
	 * Retorna uma lista de inteiros com os endpoints de todos os pull requests associados
	 * a um projeto 
	 */
	public List<Integer> getPullsNumbers() {
		return lstPulls;
	}

}
