package com.marcelomartins.atena.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class PanelSearchResults extends JFrame{
	
	private JPanel pCenter, pNorth;
	
	private JLabel lbTit;
	
	
	public PanelSearchResults(JFrame superFrame) {	
		addPanel();
		
		setTitle("Atena Search");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}	
	
	private void buildPanel() {
			
		pCenter = new JPanel();
		pCenter.setBounds(0, 45, 750, 418);
		pCenter.setBackground(Color.WHITE);
		
		pNorth = new JPanel();
		pNorth.setBounds(0, 0, 750, 44);
		
		addLabel();
	}
	
	private void addPanel() {
		buildPanel();
		getContentPane().setLayout(null);
		getContentPane().add(pCenter);
		getContentPane().add(pNorth);
	}
	
	private void buildLabel() {
		lbTit = new JLabel("ATENA");
		lbTit.setHorizontalAlignment(SwingConstants.LEFT);
		lbTit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbTit.setBounds(12, 12, 270, 32);
	}
	
	private void addLabel() {
		buildLabel();
		pCenter.setLayout(null);
		pNorth.setLayout(null);
		pNorth.add(lbTit);
	}
	
	public static void main(String[] args) {
		PanelSearchResults psr = new PanelSearchResults(null);
		psr.setVisible(true);
		
	}

}
