package com.marcelomartins.atena.views;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private PanelSearch ps;
	private PanelSearchResults psr;
	
	private JLayeredPane lp;
	
	public Main() {
		
		addPanel();
		
		setTitle("Atena Search");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void buildPanel() {
		lp = new JLayeredPane();
		lp.setLayout(new CardLayout(0,0));
		add(lp);
	}
	
	private void addPanel() {
		buildPanel();
//		ps = new PanelSearch(this);
//		lp.add(ps);
		
//		psr = new PanelSearchResults();
//		lp.add(psr);
	}
	
	private void switchPanels(JPanel panel) {
		lp.removeAll();
		lp.add(panel);
		lp.repaint();
		lp.revalidate();
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.setVisible(true);
	}
}
