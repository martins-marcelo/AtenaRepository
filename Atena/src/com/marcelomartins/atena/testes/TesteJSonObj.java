package com.marcelomartins.atena.testes;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.marcelomartins.atena.domain.PullRequest;
import com.marcelomartins.atena.domain.repository.PullRequestRepository;

public class TesteJSonObj{

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String link;
		System.out.println("Begin");

		PullRequestRepository bc = new PullRequestRepository();

		System.out.println("Repositório: ");
		link = scanner.next();
		List<PullRequest> lstReps = bc.getPullsList(link);
		
		if(lstReps!=null)
			for (PullRequest repositorio : lstReps) {
				System.out.println(repositorio.toString());
				System.out.println("----------");
			}

	}

}
