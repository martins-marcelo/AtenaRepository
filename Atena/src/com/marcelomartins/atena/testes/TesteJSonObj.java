package com.marcelomartins.atena.testes;

import java.io.IOException;
import java.util.List;

import com.marcelomartins.atena.controllers.BuscaController;
import com.marcelomartins.atena.domain.Repositorio;



public class TesteJSonObj{

	public static void main(String[] args) throws IOException {
		
		BuscaController bc = new BuscaController();
		List<Repositorio> lstReps = bc.buscarPulls("github/scientist");
		for (Repositorio repositorio : lstReps) {
			System.out.println(repositorio.toString());
			System.out.println("----------");
		}
		
	}

}
