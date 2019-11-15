package com.marcelomartins.atena.controllers.mineradores;

import java.io.File;

import weka.associations.Apriori;

public class Associador {
	/*Classe que vai fazer o processamento em cima do arquivo csv formatado*/
	public static void main(String[] args) {
		//Arquivo a ser minerado
		File f = new File("path/name");
		/* o objeto da classe Apriori é o responsável por fazer a mineração de dados em si*/
		Apriori ap = new Apriori();
	}
}
