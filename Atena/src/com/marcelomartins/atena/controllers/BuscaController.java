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
	
	private JsonArray jarr;

	/*
	 * Make a search of all pull requests from a repository
	 * Save the attribute number from Json to use it like an endpoint 
	 * to the next search
	 * Must be linked in the view button 'search'
	 */
	public List<Repositorio> buscarPulls(String rep) throws IOException{
		int iter = 1;
		//ArrayList para capturar numeros dos pull requests
		List<Integer> lstPulls = new ArrayList<Integer>();
		//ArrayList of Repositorio objects that capture from Json,
		//only the attributes that has been specificated
		List<Repositorio> lstReps = new ArrayList<Repositorio>();
		
		/* Fase 1: Retrieve the endpoints to pulls pages*/
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
		
		/* Fase 2:Save in Java Objects the desired attributes from JsonObject*/
		for(int j = 0; j < lstPulls.size(); j++) {
			Repositorio r = new Repositorio();
			URL url = new URL(
					"https://api.github.com/repos/"+rep+"/pulls/"+lstPulls.get(j));
			System.out.println("URL begin at: "+url);
			InputStream ins = url.openStream();
			JsonReader rdr = Json.createReader(ins);
			JsonObject obj = rdr.readObject();
			
			r.setCommits(obj.getInt("commits"));
			r.setChanged_files(obj.getInt("changed_files"));
			int changed_lines = obj.getInt("additions") + obj.getInt("deletions");
			r.setChanged_lines(changed_lines);
			
			/*
			if( tempo até 23:59:59) {
				r.setLifetime("short");
			}
			else if(tempo de 24:00:00 até 47:59:59) {
				r.setLifetime("medium");
			}
			else if(tempo > 48:00:00){
				r.setLifetime("long");
			}
			*/
			r.setMerged(obj.getBoolean("merged"));
			
			/* Ainda vou tratar a excecao do merged by, pois 
			 * pulls fechados nao tem esse atributo se nao me engano
			 
			if(r.isMerged()) 
				r.setMerged_by(obj.getString("merged_by"));
			else
				//Tratar
				r.setMerged_by(null);
			*/			
			r.setComments(obj.getInt("comments"));
			lstReps.add(r);
		}
		return lstReps;		
	}
	

}
