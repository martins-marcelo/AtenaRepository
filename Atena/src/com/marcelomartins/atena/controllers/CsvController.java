package com.marcelomartins.atena.controllers;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.marcelomartins.atena.domain.PullRequest;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CsvController {
	public void parseAndWrite(List<PullRequest> lstPulls) throws Exception{ 
//	    String path = new File("").getAbsolutePath();;
        Writer writer = Files.newBufferedWriter(Paths.get("pessoas.csv"));
        StatefulBeanToCsv<PullRequest> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

        beanToCsv.write(lstPulls);

        writer.flush();
        writer.close();
	     System.out.println("Finished! Go, look");
	}
}
