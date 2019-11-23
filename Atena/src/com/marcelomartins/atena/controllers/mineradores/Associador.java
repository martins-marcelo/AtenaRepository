	package com.marcelomartins.atena.controllers.mineradores;

import java.nio.file.Path;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class Associador {
	DataSource ds;
	Instances ins, discret;
	Apriori ap;
	Discretize disc;
	String[] options;
	public void minerarApriori(Path caminho) throws Exception {
		options = new String[5];
		options[0] = "-B";
		options[1] = "5";
		options[2] = "-R";
		options[3] = "1-9";
		options[4] = "-V";
		ds = new DataSource("pullrequests2.csv");
		ins = ds.getDataSet();
		ins.setClassIndex(7);
		System.out.println("numero de atributos"+ins.numAttributes());
		System.out.println("numero de instancias"+ins.numInstances());
		//disc = new Discretize();
		//disc.setOptions(options);
		//disc.setInputFormat(ins);
		//ins = Filter.useFilter(ins, disc);
		ap = new Apriori();
		ap.buildAssociations(ins);
		System.out.println(ap);
		
		
	}
}
