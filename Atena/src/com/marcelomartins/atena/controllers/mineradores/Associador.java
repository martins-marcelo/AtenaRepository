	package com.marcelomartins.atena.controllers.mineradores;



import java.nio.file.Files;
import java.nio.file.Paths;

import com.marcelomartins.atena.views.AprioriResults;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class Associador {
	String[] options;
	String destArchName = "pullRequestsDisc.csv";
	DataSource ds;
	Instances ins, newData;
	Apriori ap;
	Discretize disc;
	CSVSaver saver;
	public void minerarApriori(String arquivoNome) throws Exception {
		/*
		 * This String array represents metrics to Discretize
		 * See docs for more explanation
		 */
		ds = new DataSource(arquivoNome);
		ins = ds.getDataSet();
		ins.setClassIndex(7);
		System.out.println("numero de atributos "+ins.numAttributes());
		System.out.println("numero de instancias "+ins.numInstances());
		
		//Discretizing
		disc = new Discretize();
		disc.setInputFormat(ins);
		
		newData = Filter.useFilter(ins, disc);
		System.out.println("Discretizou!");
		System.out.println(newData.toString());
//		CSVSaver saver = new CSVSaver();
//		saver.setInstances(newData);
//		saver.setFile(new File(destArchName));
//		saver.writeBatch();
		
		//Making association
		ap = new Apriori();
		ap.buildAssociations(newData);
		System.out.println(ap);
		
		AprioriResults apr = new AprioriResults(ap);
		apr.setVisible(true);
		Files.write(Paths.get("aprioriResult.txt"), ap.toString().getBytes());
		
		
	}
}
