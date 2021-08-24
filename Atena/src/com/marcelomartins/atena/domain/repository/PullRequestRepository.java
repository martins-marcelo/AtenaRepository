package com.marcelomartins.atena.domain.repository;

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

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.marcelomartins.atena.domain.PullRequest;

public class PullRequestRepository {

	private final static String CLI_ID = "";
	private final static String CLI_SECRET = "";

	/*
	 * Make a search of all pull requests from a repository
	 * Save the attribute number from Json to use it like an endpoint 
	 * to the next search
	 * Must be linked in the view button 'search'
	 */
	public List<PullRequest> getPullsList(String rep) throws IOException{
		
		//Before, the whole method getPullsNunbers was called step 1
		List<Integer> lstNumbers = getPullsNumbers(rep);
		List<PullRequest> lstPulls = new ArrayList<PullRequest>();
		
		//This was called step 2
		for(int i=0; i<lstNumbers.size(); i++)
			lstPulls.add(getPullRequest(rep, lstNumbers.get(i)));
		
		
		return lstPulls;
	}

	//Step 1
	private List<Integer> getPullsNumbers(String rep) throws IOException{
		
		URL url;
		InputStream ins;
		JsonReader rdr;
		JsonStructure str;
		JsonArray jarr;
		
		int i = 1;

		List<Integer> lstNumbers = new ArrayList<Integer>();

		do {
			url = new URL("https://api.github.com/repos/"+rep+"/pulls?state=closed&page="+i+
					"&client_id="+CLI_ID+"&client_secret="+CLI_SECRET);
			ins = url.openStream();

			rdr = Json.createReader(ins);
			str = rdr.read();

			if(str.asJsonArray().size()>0)
				jarr = str.asJsonArray();
			else
				break;

			System.out.println("Search numbers on page "+i);
			for(int j=0; j<jarr.size(); j++) {
				lstNumbers.add(jarr.getJsonObject(j).getInt("number"));
				System.out.println("Number "+jarr.getJsonObject(j).getInt("number")+" add");
			}
			i++;

		}while(jarr.size() > 0);
		System.out.println(lstNumbers.size()+" numbers add");
		
		if(lstNumbers.size()==0)
			throw new IOException();
		
		return lstNumbers;
	}
	
	//Step 2
	private PullRequest getPullRequest(String rep, int number) throws IOException{
		
		PullRequest pull = new PullRequest();
		URL url = new URL("https://api.github.com/repos/"+rep+"/pulls/"+number+
					"?client_id="+CLI_ID+"&client_secret="+CLI_SECRET);
		System.out.println("URL begin at: "+url);
		InputStream ins = url.openStream();
		JsonReader rdr = Json.createReader(ins);
		JsonObject obj = rdr.readObject();
		//Object that is able to access one level below the object
		JsonObject joMerged_by;
		
		pull.setNumber(obj.getInt("number"));
		pull.setCommits(obj.getInt("commits"));
		pull.setChanged_files(obj.getInt("changed_files"));
		int changed_lines = obj.getInt("additions") + obj.getInt("deletions");
		pull.setChanged_lines(changed_lines);
		pull.setLifetime(lifeTimeFactory(obj.getString("created_at"), obj.getString("closed_at")));
		pull.setLifetimeType(lifeTimeTypeFactory(pull.getLifetime()));
		pull.setMerged(obj.getBoolean("merged"));
		pull.setComments(obj.getInt("comments"));
		if(pull.isMerged()) {
			joMerged_by = obj.getJsonObject("merged_by");
			pull.setMerged_by(joMerged_by.getString("login"));
		}
		else {
			pull.setMerged_by(null);
		}
		return pull;
	}
	
	/*
	 * Return a String with type of lifetime and set hours to Pull attribute 
	 */
	private String lifeTimeTypeFactory(int hours) {

		if(hours >= 0 && hours <= 24)
			return "VS";
		else if (hours > 24 && hours <= 72)
			return "S";
		else if (hours > 72 && hours <= 240)
			return "M";
		else if (hours > 240)
			return "L";

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
