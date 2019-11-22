package com.marcelomartins.atena.testes;

import java.util.Scanner;
import javax.swing.JFrame;
import com.marcelomartins.atena.views.Main;
import com.marcelomartins.atena.views.PanelSearch;

public class TesteViews {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		Main mn = new Main();	
		PanelSearch ps = new PanelSearch();
//		PanelSearchResults psr = new PanelSearchResults(null);
		
		System.out.println("Aperte ENTER");
		System.out.println(new Scanner(System.in).nextLine());
		
		frame = ps;
		frame.setVisible(true);
		
		System.out.println("Aperte ENTER");
		System.out.println(new Scanner(System.in).nextLine());
		
		frame.setVisible(false);
//		frame = psr;
		frame.setVisible(true);
		
		
		
	}

}
