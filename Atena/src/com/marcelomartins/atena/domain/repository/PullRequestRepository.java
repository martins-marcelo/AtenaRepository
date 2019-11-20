package com.marcelomartins.atena.domain.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.JodaTimePermission;

import com.marcelomartins.atena.domain.PullRequest;

public class PullRequestRepository {

	private final static String CLI_ID = "d95b5880e8a566cf210d";
	private final static String CLI_SECRET = "cb66a2efc7c7e50e2921bc93c17dfdffdb5aa97b";

	private JsonArray jarr;

	/*
	 * Make a search of all pull requests from a repository
	 * Save the attribute number from Json to use it like an endpoint 
	 * to the next search
	 * Must be linked in the view button 'search'
	 */
	public List<PullRequest> getPullsList(String rep) throws IOException{

		int iter = 1;
		//ArrayList para capturar numeros dos pull requests
		List<Integer> lstPulls = new ArrayList<Integer>();
		//ArrayList of Repositorio objects that capture from Json,
		//only the attributes that has been specificated
		List<PullRequest> lstReps = new ArrayList<PullRequest>();

		/* Fase 1: Retrieve the endpoints to pulls pages*/
		do {


			URL url = new URL(
					"https://api.github.com/repos/"+rep+"/pulls?state=closed&page="+iter+
					"&client_id="+CLI_ID+"&client_secret="+CLI_SECRET);
			InputStream ins = url.openStream(); 

			JsonReader rdr = Json.createReader(ins);
			JsonStructure stru = rdr.read();

			if(stru.asJsonArray().size()>0) {
				jarr = stru.asJsonArray();
			}	
			else
				break;
			System.out.println("URL begin at: "+url);
			for(int j = 0; j < jarr.size(); j++) {
				lstPulls.add(jarr.getJsonObject(j).getInt("number"));
			}
			System.out.println("Stage "+iter+" succesful! "+lstPulls.size());
			iter++;
		}while(jarr.size() > 0);
		/* Fim da fase 1*/

		/* Fase 2:Save in Java Objects the desired attributes from JsonObject*/
		for(int j = 0; j < lstPulls.size(); j++) {
			PullRequest r = new PullRequest();
			URL url = new URL(
					"https://api.github.com/repos/"+rep+"/pulls/"+lstPulls.get(j)+
					"?client_id="+CLI_ID+"&client_secret="+CLI_SECRET);
			System.out.println("URL begin at: "+url);
			InputStream ins = url.openStream();
			JsonReader rdr = Json.createReader(ins);
			JsonObject obj = rdr.readObject();

			r.setNumber(obj.getInt("number"));
			r.setCommits(obj.getInt("commits"));
			r.setChanged_files(obj.getInt("changed_files"));
			int changed_lines = obj.getInt("additions") + obj.getInt("deletions");
			r.setChanged_lines(changed_lines);
			r.setLifetime(lifeTimeFactory(obj.getString("created_at"), obj.getString("closed_at")));
			r.setLifetimeType(lifeTimeTypeFactory(r.getLifetime()));
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

	/*
	 * Return a String with type of lifetime and set hours to Pull attribute 
	 */
	private String lifeTimeTypeFactory(int hours) {

		if(hours >= 0 && hours <= 24)
			return "vs";
		else if (hours > 24 && hours <= 72)
			return "s";
		else if (hours > 72 && hours <= 240)
			return "m";
		else if (hours > 240)
			return "l";

		return null;
	}
	/*
	 * Return a the lifetime of pull in hours
	 */
	private int lifeTimeFactory(String created, String closed) {

		DateTime createdAt = dateTimeFactory(created);
		DateTime closedAt = dateTimeFactory(closed);

		int horas = Hours.hoursBetween(closedAt, createdAt).getHours();

		return horas*-1;
	}

	/*
	 * Build a DateTime object using string taken from JSon Object
	 */
	private DateTime dateTimeFactory(String dateTime) {

		String date = dateTime.substring(0, 10);
		String time = dateTime.substring(11, 19);

		int[] DATE = {Integer.parseInt(date.split("-")[0]), 
				Integer.parseInt(date.split("-")[1]),
				Integer.parseInt(date.split("-")[2])};
		int[] TIME = {Integer.parseInt(time.split(":")[0]), 
				Integer.parseInt(time.split(":")[1]),
				Integer.parseInt(time.split(":")[2])};

		DateTime dt = new DateTime(DATE[0], DATE[1], DATE[2], TIME[0], TIME[1], TIME[2]);
		return dt;
	}

}
