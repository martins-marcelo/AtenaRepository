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
	
	private JPanel pCenter, pSouth;
	
	private JLabel lbTit, lbInfo;
	
	private JButton btSearch;
	
	private JTextField tfSearch;
	
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
		pCenter.setBounds(0, 0, 750, 431);
		pCenter.setBackground(Color.WHITE);
		
		pSouth = new JPanel();
		pSouth.setBounds(0, 431, 750, 44);
		
		addLabel();
		addSearchArea();
	}
	
	private void addPanel() {
		buildPanel();
		setLayout(null);
		add(pCenter);
		add(pSouth);
	}
	
	private void buildLabel() {
		lbTit = new JLabel("ATENA");
		lbTit.setBounds(240, 105, 270, 84);
		lbTit.setHorizontalAlignment(SwingConstants.CENTER);
		lbTit.setFont(new Font("DejaVu Sans", Font.BOLD, 72));
		
		lbInfo = new JLabel("About");
		lbInfo.setBounds(34, 0, 96, 44);
		lbInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lbInfo.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
	}
	
	private void addLabel() {
		buildLabel();
		pCenter.setLayout(null);
		pCenter.add(lbTit);
		pSouth.setLayout(null);
		pSouth.add(lbInfo);
	}

	private void buildSearchArea() {
		tfSearch = new JTextField();	
		tfSearch.setForeground(Color.LIGHT_GRAY);
		tfSearch.setText("username/respository");
		tfSearch.setToolTipText("");
		tfSearch.setBounds(210, 223, 247, 44);
		tfSearch.setFont(new Font("DejaVu Sans", Font.PLAIN, 17));
		tfSearch.setFocusable(false);
		tfSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==tfSearch) {
					tfSearch.setFocusable(true);
					tfSearch.setText("");
					tfSearch.setForeground(Color.BLACK);
				}
			}
		});
		
		btSearch = new JButton("Search");
		btSearch.setBackground(Color.WHITE);
		btSearch.setBounds(469, 222, 99, 44);
		btSearch.setFont(new Font("DejaVu Sans", Font.BOLD, 17));
	}
	
	private void addSearchArea() {
		buildSearchArea();
		pCenter.add(tfSearch);
		pCenter.add(btSearch);
	}
}
