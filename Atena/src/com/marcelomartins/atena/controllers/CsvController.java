package com.marcelomartins.atena.controllers;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.marcelomartins.atena.controllers.mineradores.Associador;
import com.marcelomartins.atena.domain.PullRequest;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CsvController {
	public void writePulls(List<PullRequest> lstPulls) throws Exception{
		
		JFileChooser escolheArquivo = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*csv", "csv");
		escolheArquivo.setFileFilter(filtro);
		int resposta = escolheArquivo.showOpenDialog(new JDialog());
		if (resposta == JFileChooser.APPROVE_OPTION) {
			//Seta caminho do arquivo
			try {
				Writer writer = Files.newBufferedWriter(Paths.get(escolheArquivo.getSelectedFile().getPath().toString()));
				StatefulBeanToCsv<PullRequest> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

				beanToCsv.write(lstPulls);

				writer.flush();
				writer.close();
				JOptionPane.showMessageDialog(null, "Arquivo CSV gerado com sucesso");
			}
			catch(CsvRequiredFieldEmptyException csvrfe) {
				JOptionPane.showMessageDialog(null, csvrfe.getMessage());
			}
			catch(CsvDataTypeMismatchException csvtme) {
				JOptionPane.showMessageDialog(null, csvtme.getMessage());
			}
			catch(IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe.getMessage());
			}
			
		}
		
	}
	
	public void writeDataSource() {
		
	}
	
	public void retrieveCSVFile() {
		
	}
}
