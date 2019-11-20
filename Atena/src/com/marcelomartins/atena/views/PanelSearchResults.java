package com.marcelomartins.atena.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.marcelomartins.atena.controllers.SearchController;
import com.marcelomartins.atena.domain.PullRequest;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class PanelSearchResults extends JFrame{
	
	private List<PullRequest> lstPulls;
	
	private DefaultTableModel model;
	
	private JPanel pCenter, pNorth;

	private JLabel lbTit, lbLink, lbExit;

	private JTable table;
	
	private String link;

	public PanelSearchResults(String link, List<PullRequest> lstPulls) {
		
		this.link = link;
		this.lstPulls = lstPulls;
		
		addPanel();

		setTitle("Atena Search");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}	

	private void buildPanel() {

		pCenter = new JPanel();
		pCenter.setBounds(0, 45, 750, 430);
		pCenter.setBackground(Color.WHITE);

		pNorth = new JPanel();
		pNorth.setBounds(0, 0, 750, 44);

		addLabel();
		addTable();
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

		lbLink = new JLabel(link);
		lbLink.setHorizontalAlignment(SwingConstants.CENTER);
		lbLink.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbLink.setBounds(115, 12, 520, 32);

		lbExit = new JLabel("Exit");
		lbExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VOLTAR PARA A JANELA ANTERIOR
				dispose();
				PanelSearch ps = new PanelSearch();
				ps.setVisible(true);
			}
		});
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lbExit.setBounds(659, 12, 79, 32);
	}

	private void addLabel() {
		buildLabel();
		pCenter.setLayout(null);
		pNorth.setLayout(null);
		pNorth.add(lbTit);
		pNorth.add(lbLink);
		pNorth.add(lbExit);
	}

	//Construir a tabela inicializando com os objetos retornados da consulta
	private void buildTable() {
		
		model = new DefaultTableModel();
		model.addColumn("Number");
		model.addColumn("Commits");
		model.addColumn("Changed Files");
		model.addColumn("Changed Lines");
		model.addColumn("Lifetime Type");
		model.addColumn("Lifetime");
		model.addColumn("Merged by");
		model.addColumn("Merged");
		model.addColumn("Comments");		
		loadData();
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(191, 65, 506, 300);
		
	}
	
	private void loadData() {
		model.setNumRows(0);
		
		for(PullRequest pull : lstPulls) {
			model.addRow(new Object[] 
					{pull.getNumber(),					
					pull.getCommits(),
					pull.getChanged_files(),
					pull.getChanged_lines(),
					pull.getLifetimeType(),
					pull.getMerged_by(),
					null, //NÃO SEI SE TEM GET MERGED SEI LÁ, NÃO ENCONTREI
					pull.getComments()
					});
		}
	}

	private void addTable() {
		buildTable();
		pCenter.add(table);
	}
}
