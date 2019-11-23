package com.marcelomartins.atena.controllers.mineradores;

import java.nio.file.Path;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Associador {
	DataSource ds;
	Instances ins;
	Apriori ap;
	public void minerarApriori(Path caminho) throws Exception {
		ds = new DataSource("pullrequests.csv");
		ins = ds.getDataSet();
		ins.setClassIndex(7);
		System.out.println("numero de atributos"+ins.numAttributes());
		System.out.println("numero de instancias"+ins.numInstances());
		ap = new Apriori();
		
	}
}
