package com.marcelomartins.atena.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.marcelomartins.atena.controllers.SearchController;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.UIManager;

public class PanelSearch extends JFrame{

	private SearchController searchController;

	private JPanel pCenter, pSouth, pError;

	private JLabel lbTit, lbInfo, lbError;

	private JButton btSearch;

	private JTextField tfSearch;

	public PanelSearch() {	
		addPanel();
		searchController = new SearchController(this);
		
		setTitle("Atena Search");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}	

	private void buildPanel() {

		pCenter = new JPanel();
		pCenter.setLayout(null);
		pCenter.setBounds(0, 0, 750, 431);
		pCenter.setBackground(Color.WHITE);

		pSouth = new JPanel();
		pSouth.setLayout(null);
		pSouth.setBounds(0, 431, 750, 44);

		pError = new JPanel();
		pError.setLayout(null);
		pError.setBounds(251, 279, 247, 44);
		pError.setVisible(false);

		addLabel();
		addSearchArea();
	}

	private void addPanel() {
		buildPanel();
		getContentPane().setLayout(null);

		pCenter.add(pError);	
		getContentPane().add(pCenter);
		getContentPane().add(pSouth);
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

		lbError = new JLabel("Repository not found!");
		lbError.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		lbError.setBounds(12, 0, 223, 44);
		lbError.setHorizontalAlignment(SwingConstants.CENTER);
		lbError.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
	}

	private void addLabel() {
		buildLabel();

		pCenter.add(lbTit);

		pError.add(lbError);

		pSouth.add(lbInfo);
	}

	private void buildSearchArea() {
		tfSearch = new JTextField();	
		tfSearch.setForeground(Color.LIGHT_GRAY);
		tfSearch.setText("username/respository");
		tfSearch.setToolTipText("");
		tfSearch.setBounds(251, 223, 247, 44);
		tfSearch.setFont(new Font("DejaVu Sans", Font.PLAIN, 17));
		tfSearch.setFocusable(false);
		tfSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==tfSearch) {
					tfSearch.setFocusable(true);
					tfSearch.setText("");
					tfSearch.setForeground(Color.BLACK);
					
					if(pError.isVisible())
						showErrorMessage(false);
				}
			}
		});

		btSearch = new JButton("Search");
		btSearch.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==btSearch) {
					searchController.searchPulls(tfSearch.getText());
				}	
			}
		});
		btSearch.setBackground(Color.WHITE);
		btSearch.setBounds(510, 223, 99, 44);
		btSearch.setFont(new Font("DejaVu Sans", Font.BOLD, 17));
	}

	private void addSearchArea() {
		buildSearchArea();
		pCenter.add(tfSearch);
		pCenter.add(btSearch);
	}

	public void showErrorMessage(boolean value) {
		pError.setVisible(value);
	}


	public static void main(String[] args) {
		PanelSearch ps = new PanelSearch();
	}
}
